package controller;

import dto.EditalDTO;
import enumered.DiaSemanaEnum;
import exception.MatriculaBusinessException;
import model.Anexo;
import model.Edital;
import model.Instituicao;
import model.Matricula;
import model.Viagem;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import service.AnexoService;
import service.EditalService;
import service.InstituicaoService;
import service.MatriculaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@ViewScoped
@Named(value = "matriculaMB")
public class MatriculaMB implements Serializable {
	private static final long serialVersionUID = -7785394172005232068L;

	@Inject
	private MatriculaService matriculaService;

	@Inject
	private EditalService editalService;

	@Inject
    private Identity identity;

	@Inject
	private InstituicaoService instituicaoService;

	@Inject
    private MatriculaAnexoMB matriculaAnexoMB;

	private Matricula matricula;

	private Edital edital;

	private Viagem viagem;

	private String[] sentido = new String[2];

	private List<Viagem> viagens = new ArrayList<>();

    private List<Edital> listaEditais = new ArrayList();

	private List<Instituicao> instituicoes = new ArrayList();

	@Inject
	@Param
	private Long idEdital;

	@PostConstruct
	public void init() {
	    carregarMatricula();
        carregarEdital();
	}

    private void carregarListaEditais() {
        listaEditais = editalService.consultarEdital(new EditalDTO());
    }

    private void abrirModalEditais(){
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEditais').show();");
    }

	private void novaViagem() {
		viagem = new Viagem();
	}

	public void carregarMatricula() {
		if(identity.isUsuarioEstudante()){
			matricula = matriculaService.obterMatricula(identity.getUsuario().getEstudante().getIdEstudante());

			if(matricula == null) {
				matricula = new Matricula();
				matricula.setEstudante(identity.getUsuario().getEstudante());
			} else {
				viagens = matricula.getViagens();
				idEdital = matricula.getEdital().getIdEdital();
			}
        } else {
		    Messages.addError(null, "Apenas estudantes podem realizar a matrícula!");
        }

        matriculaAnexoMB.setMatricula(matricula);
	}

	public void removerViagem(Viagem viagem) {
	    viagens.remove(viagem);
        Messages.addInfo(null, "Viagem removida com sucesso!");
    }

	public void salvarViagem(){
		for(int i = 0; i < sentido.length; i++) {
			Viagem viagemMatricula = new Viagem();
			viagemMatricula.setDiaSemana(viagem.getDiaSemana());
			viagemMatricula.setInstituicao(viagem.getInstituicao());
			viagemMatricula.setSentido(sentido[i]);
			viagemMatricula.setMatricula(matricula);
			viagens.add(viagemMatricula);
		}

		Messages.addInfo(null, "Viagem adicionada com sucesso!");
		novaViagem();
		sentido = new String[2];
	}

	public void salvarMatricula(){
		try {
			matricula.setInscricao(LocalDateTime.now());
			matricula.setAnexos(matriculaAnexoMB.anexosAdicioandos());
			matricula.setEstudante(identity.getUsuario().getEstudante());
			matricula.setEdital(this.edital);
			matricula.setViagens(viagens);
			matriculaService.salvar(matricula);
			Messages.addInfo(null, "Matricula salva com sucesso");

			matriculaAnexoMB.salvarArquivosTemporarios();
		} catch (MatriculaBusinessException e) {
			e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
		}
	}

    public void enviarArquivo(FileUploadEvent event) {
	    if(matriculaAnexoMB.getMatricula() == null)
	        matriculaAnexoMB.setMatricula(matricula);

	    matriculaAnexoMB.enviarArquivoTemporario(event);
    }

	private void carregarEdital() {
		if(idEdital == null) {
			carregarListaEditais();
			abrirModalEditais();

		}
		if (idEdital != null) {
			edital = editalService.listarEdital(idEdital);
		}
		listarInstituicao();
		novaViagem();
	}

	public String obterPeriodo() {
		if (edital == null) {
			return "";
		}
		String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Período de inscrição: " + formato.format(edital.getInicio()) + " à "
				+ formato.format(edital.getTermino()) + aberto;
	}

    public String obterPeriodo(Edital edital) {
        if (edital == null) {
            return "Edital Não Carregado";
        }
        String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Período de inscrição: " + formato.format(edital.getInicio()) + " à "
                + formato.format(edital.getTermino()) + aberto;
    }
	
	public DiaSemanaEnum[] obterDiaSemana() {
		return DiaSemanaEnum.values();
	}

	public void listarInstituicao() {
		instituicoes = instituicaoService.obterInstituicoesEnsino();
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
}
