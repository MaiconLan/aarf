package controller;


import service.InstituicaoService;
import service.MatriculaService;
import service.ViagemService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import dto.FiltroViagemDTO;
import model.Instituicao;
import model.Matricula;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "matriculaAprovacaoMB")
public class MatriculaAprovacaoMB implements Serializable {
	private static final long serialVersionUID = -7785394172005232068L;

	@Inject
	private MatriculaService matriculaService;
	
	@Inject
	private ViagemService viagemService;
    
    @Inject
    private InstituicaoService instituicaoService;
	
    private List<Instituicao> instituicoes;
	private List<Matricula> listMatricula;
	private Matricula filtro;
	private Matricula matriculaSelecionada;
	
	@PostConstruct
	public void init() {
		limpar();
		
	}
	
	public void limpar() {
		this.matriculaSelecionada = new Matricula();
		this.listMatricula = new ArrayList<>();
		this.filtro = new Matricula();
		carregarInstituicoes();
		buscarMatricula();
	}
	
	public void reprovarMatricula(Matricula m) {
		matriculaService.recusarMatricula(m);
		Messages.addInfo(null, "Matricula aprovada com sucesso!");
		buscarMatricula();
	}
	
	public void aprovarMatricula(Matricula m) {
		matriculaService.aprovarMatricula(m);
		Messages.addInfo(null, "Matricula recusada com sucesso!");
		buscarMatricula();
	}
	
	private void carregarInstituicoes(){
        instituicoes = instituicaoService.obterInstituicoesEnsino();
        buscarMatricula();
    }
	
	public void consultarDetalhes(Matricula m) {
		this.matriculaSelecionada = m;
		
		FiltroViagemDTO filtroViagemDTO = new FiltroViagemDTO();
		filtroViagemDTO.setIdEdital(m.getEdital().getIdEdital());		
		filtroViagemDTO.setIdMatricula(m.getIdMatricula());		
		
		this.matriculaSelecionada.setViagens(viagemService.buscarViagens(filtroViagemDTO));
		RequestContext.getCurrentInstance().update("modalDetalhesEstudante");
		RequestContext.getCurrentInstance().execute("PF('modalDetalhesEstudante').show();");
	}
	
	public void buscarMatricula(){
		 this.listMatricula = matriculaService.listarMatriculas(this.filtro);
	}

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

	public Matricula getFiltro() {
		return filtro;
	}

	public void setFiltro(Matricula filtro) {
		this.filtro = filtro;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	
}
