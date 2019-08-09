package controller;

import business.EstudanteBusiness;
import business.InstituicaoBusiness;
import exception.CepBussinesException;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Endereco;
import model.Estudante;
import model.Instituicao;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import service.CepService;
import utils.email.EmailHtml;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("usuarioMB")
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Estudante estudante;

    private List<Instituicao> instituicoes;

    @Inject
    private EstudanteBusiness estudanteBusiness;

    @Inject
    private CepService cepService;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @PostConstruct
    public void init() {
        novoEstudante();
        carregarInstituicoes();
    }

    private void carregarInstituicoes(){
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
    }

    public void salvarEstudante() {
        try {
            estudanteBusiness.salvarEstudante(estudante);
            enviarEmail();

            Faces.redirect("/public/bem-vindo.xhtml");

        } catch (EstudanteBusinessException | LoginException e) {
            e.getMessages().forEach(m -> Messages.addError(null, m));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consultarCep() {
        String cep = estudante.getPessoa().getEndereco().getCep();
        Long idEndereco = estudante.getPessoa().getEndereco().getIdEndereco();
        if (cep != null && !cep.isEmpty()) {
            try {
                Endereco endereco = cepService.getEnderecoCompleto(cep);
                endereco.setIdEndereco(idEndereco);
                endereco.setPessoa(estudante.getPessoa());
                estudante.getPessoa().setEndereco(endereco);
            } catch (CepBussinesException e) {
                Messages.addWarn(null, e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private void novoEstudante() {
        estudante = new Estudante();
        estudante.getUsuario().setAlterarLogin(true);
    }

    private void direcionaLogin(){
        try {
            String redirect = "public/home";
            Faces.redirect(redirect);
        }catch (Exception e){

        };
    }

    private void enviarEmail(){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String mensagem = IOUtils.toString(classLoader.getResource("template_email/email_cadastro_usuario.html"), "UTF-8");
            String emailDestinatario = estudante.getPessoa().getEmail();
            String genero = estudante.getPessoa().getGenero();
            String recepcao = genero.equals("M") ? "Bem-Vindo" : genero.equals("F") ? "Bem-Vinda" : "Bem-Vindo(a)";

            mensagem = mensagem.replaceAll(":recepcao:", recepcao);
            mensagem = mensagem.replaceAll(":nomeEstudante:", estudante.getPessoa().getPrimeiroNome());
            mensagem = mensagem.replaceAll(":endereco:", "Endereço");
            mensagem = mensagem.replaceAll(":numero:", "Número");
            mensagem = mensagem.replaceAll(":telefone:", "Telefone");

            new EmailHtml("AARF", mensagem, emailDestinatario).enviar();
            direcionaLogin();

        } catch (IOException e) {
            e.printStackTrace();
            Messages.addWarn(null, "Ocorreu um erro ao enviar o e-mail");
        }
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

}
