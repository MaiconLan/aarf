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
import model.Edital;
import model.Instituicao;
import model.Matricula;

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
		return "Período de inscrição: " + formato.format(edital.getInicio()) + " à "
				+ formato.format(edital.getTermino()) + aberto;
	}

	public void listarInstituicao() {
		instituicoes = instituicaoService.listarInstituicao();
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

}
