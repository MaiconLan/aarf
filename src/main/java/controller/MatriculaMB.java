package controller;

import business.EditalBusiness;
import business.InstituicaoBusiness;
import business.MatriculaBusiness;
import dto.EditalDTO;
import enumered.DiaSemanaEnum;
import exception.MatriculaBusinessException;
import model.Edital;
import model.Instituicao;
import model.Matricula;
import model.Viagem;
import org.apache.commons.io.IOUtils;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import utils.email.EmailHtml;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "matriculaMB")
public class MatriculaMB implements Serializable {
    private static final long serialVersionUID = -7785394172005232068L;

    @Inject
    private MatriculaBusiness matriculaBusiness;

    @Inject
    private EditalBusiness editalBusiness;

    @Inject
    private Identity identity;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject
    private MatriculaAnexoMB matriculaAnexoMB;

    private Matricula matricula;

    private Edital edital;

    private Viagem viagem;

    private String[] sentido = new String[2];

    private List<Viagem> viagens = new ArrayList<>();

    private List<Edital> listaEditais = new ArrayList();

    private List<Instituicao> instituicoes = new ArrayList();

    private List<Matricula> matriculas = new ArrayList<>();

    @Inject
    @Param
    private Long idEdital;

    @PostConstruct
    public void init() {
        carregarMatricula();
        carregarEdital();
        listarMatriculas();
    }

    private void listarMatriculas() {
        matriculas = matriculaBusiness.listarMatriculasByIdEstudante(identity.getUsuario().getEstudante().getIdEstudante());
    }

    private void carregarListaEditais() {
        EditalDTO editalDTO = new EditalDTO();
        editalDTO.setOrder(false);
        listaEditais = editalBusiness.consultarEdital(editalDTO);
    }

    private void abrirModalEditais() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEditais').show();");
    }

    private void novaViagem() {
        viagem = new Viagem();
    }

    public void carregarMatricula() {
        if (identity.isUsuarioEstudante()) {
            matricula = matriculaBusiness.obterMatriculaByIdEstudante(identity.getUsuario().getEstudante().getIdEstudante());

            if (matricula == null) {
                matricula = new Matricula();
                matricula.setEstudante(identity.getUsuario().getEstudante());
            } else {
                viagens = matricula.getViagens();
                idEdital = matricula.getEdital().getIdEdital();
            }
            matriculaAnexoMB.setMatricula(matricula);
        } else {
            Messages.addError(null, "Apenas estudantes podem realizar a matrícula!");
        }

    }

    public void selecionarMatricula() {
        if (matricula != null) {
            matricula = matriculaBusiness.obterMatriculaById(matricula.getIdMatricula());
            viagens = matricula.getViagens();
            idEdital = matricula.getEdital().getIdEdital();
        } else {
            matricula = new Matricula();
            matricula.setEstudante(identity.getUsuario().getEstudante());
        }
        matriculaAnexoMB.setMatricula(matricula);
    }

    public void removerViagem(Viagem viagem) {
        viagens.remove(viagem);
        Messages.addInfo(null, "Viagem removida com sucesso!");
    }

    public void salvarViagem() {
        for (int i = 0; i < sentido.length; i++) {
            Viagem viagemMatricula = new Viagem();
            viagemMatricula.setDiaSemana(viagem.getDiaSemana());
            viagemMatricula.setInstituicao(viagem.getInstituicao());
            viagemMatricula.setSentido(sentido[i]);
            viagemMatricula.setMatricula(matricula);

            if(viagens.contains(viagemMatricula)) {
                Messages.addWarn(null, "Esta viagem já foi adicionada!");
                return;

            } else {
                viagens.add(viagemMatricula);
            }
        }

        Messages.addInfo(null, "Viagem adicionada com sucesso!");
        novaViagem();
        sentido = new String[2];
    }

    public void salvar() {
        matriculaAnexoMB.salvarArquivosTemporarios();

        Long idMatricula = matricula.getIdMatricula();
        matricula.setAnexos(matriculaAnexoMB.anexosAdicionados());
        matricula.setEstudante(identity.getUsuario().getEstudante());
        matricula.setEdital(this.edital);
        matricula.setViagens(viagens);
        matriculaBusiness.salvar(matricula);

        matriculaAnexoMB.removerArquivosTemporarios();

        Messages.addInfo(null, "Matricula salva com sucesso");

        if (idMatricula == null)
            enviarEmail();
    }

    public void enviarParaAprovacao() {
        try {
            salvar();
            matriculaBusiness.enviarParaAprovacao(matricula);
            Messages.addInfo(null, "Matrícula enviada para aprovação com sucesso!");
            Faces.redirect("/aarf/security/acompanhamento/matricula/matricula");

        } catch (MatriculaBusinessException e) {
            e.getMessages().forEach(error -> Messages.addError(null, error));

        } catch (IOException e) {
            e.printStackTrace();
            Messages.addFatal(null, e.getMessage());
        }
    }

    public void cancelar() {
        try {
            matriculaBusiness.cancelarMatricula(matricula, "Cancelado pelo estudante!");
            Messages.addInfo(null, "Matrícula cancelada com sucesso!");
            Faces.redirect("/aarf/security/acompanhamento/matricula/matricula");

        } catch (IOException e) {
            e.printStackTrace();
            Messages.addFatal(null, e.getMessage());
        }
    }

    public boolean desabilitarCampos() {
        return renderizarCamposMatriculaSalva() && !matricula.isInscricao() || idEdital == null;
    }

    public boolean renderizarCancelarMatricula() {
        return renderizarCamposMatriculaSalva() && (matricula.isMatriculado() || matricula.isEmAprovacao());
    }

    public boolean renderizarCamposMatriculaSalva() {
        return matricula.getIdMatricula() != null;
    }

    public boolean renderizarSelecionarMatricula() {
        return false;
        // return matriculas != null && !matriculas.isEmpty();
    }

    private void enviarEmail() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String mensagem = IOUtils.toString(classLoader.getResource("template_email/email_matricula_realizada.html"), "UTF-8");
            String emailDestinatario = matricula.getEstudante().getPessoa().getEmail();
            String genero = matricula.getEstudante().getPessoa().getGenero();
            String recepcao = genero.equals("M") ? "Bem-Vindo" : genero.equals("F") ? "Bem-Vinda" : "Bem-Vindo(a)";

            mensagem = mensagem.replaceAll(":recepcao:", recepcao);
            mensagem = mensagem.replaceAll(":nomeEstudante:", matricula.getEstudante().getPessoa().getPrimeiroNome());
            mensagem = mensagem.replaceAll(":edital:", edital.getTitulo());
            mensagem = mensagem.replaceAll(":endereco:", "Endereço");
            mensagem = mensagem.replaceAll(":numero:", "Número");
            mensagem = mensagem.replaceAll(":telefone:", "Telefone");

            new EmailHtml("AARF", mensagem, emailDestinatario).enviar();

        } catch (IOException e) {
            e.printStackTrace();
            Messages.addWarn(null, "Ocorreu um erro ao enviar o e-mail");
        }
    }

    public void enviarArquivo(FileUploadEvent event) {
        if (matriculaAnexoMB.getMatricula() == null)
            matriculaAnexoMB.setMatricula(matricula);

        matriculaAnexoMB.enviarArquivoTemporario(event);
    }

    private void carregarEdital() {
        if (idEdital == null) {
            carregarListaEditais();
            abrirModalEditais();
        }
        if (idEdital != null) {
            edital = editalBusiness.listarEdital(idEdital);
        }
        listarInstituicao();
        novaViagem();
    }

    public String obterPeriodo() {
        return obterPeriodo(edital);
    }

    public String obterPeriodo(Edital edital) {
        if (edital == null) {
            return "Edital não carregado";
        }
        String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formato.format(edital.getInicio()) + " à "
                + formato.format(edital.getTermino()) + aberto;
    }

    public DiaSemanaEnum[] obterDiaSemana() {
        return DiaSemanaEnum.values();
    }

    public void listarInstituicao() {
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public String[] getSentido() {
        return sentido;
    }

    public void setSentido(String[] sentido) {
        this.sentido = sentido;
    }

    public List<Edital> getListaEditais() {
        return listaEditais;
    }

    public void setListaEditais(List<Edital> listaEditais) {
        this.listaEditais = listaEditais;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
