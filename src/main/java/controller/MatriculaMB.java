package controller;

import service.EditalService;
import service.InstituicaoService;
import service.MatriculaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;
import org.omnifaces.util.Messages;

import dto.InstituicaoDTO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import exception.MatriculaBusinessException;
import model.DiaSemana;
import model.Edital;
import model.Instituicao;
import model.Matricula;
import model.Viagem;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "matriculaMB")
public class MatriculaMB implements Serializable {
	private static final long serialVersionUID = -7785394172005232068L;

	@Inject
	private MatriculaService matriculaService;

	private Matricula matricula;

	@Inject
	private EditalService editalService;

	private Edital edital;

	private Viagem viagem;
	private List<Viagem> viagens;
	
	@Inject
	private InstituicaoService instituicaoService;

	private List<Instituicao> instituicoes = new ArrayList();

	@Inject
	@Param
	private Long idEdital;

	@PostConstruct
	public void init() {
		novaMatricula();
		carregarEdital();
		listarInstituicao();
		novaViagem();
	}
	
	private void novaViagem() {
		viagem = new Viagem();
	}

	public void novaMatricula() {
		matricula = new Matricula();
	}

	public void salvarMatricula(){
		try {
			matriculaService.salvar(matricula);
			Messages.addInfo(null, "Matricula salva com sucesso");
		} catch (MatriculaBusinessException e) {
			e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
		}
	}

	private void carregarEdital() {
		if (idEdital != null) {
			edital = editalService.listarEdital(idEdital);
		}
	}

	public String obterPeriodo() {
		if (edital == null) {
			return "";
		}
		String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Per�odo de inscri��o: " + formato.format(edital.getInicio()) + " � "
				+ formato.format(edital.getTermino()) + aberto;
	}
	
	public DiaSemana[] obterDiaSemana() {
		return DiaSemana.values();
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

}
