package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.*;
import org.primefaces.context.RequestContext;

import dto.EstudanteDTO;
import dto.InstituicaoDTO;
import service.InstituicaoService;

@ViewScoped
@Named("instituicaoMB")
public class InstituicaoMB implements Serializable{

	private static final long serialVersionUID = 1L;

	private Instituicao instituicao;
	
	private InstituicaoDTO instituicaoDTO = new InstituicaoDTO();

	private List<Cidade> cidades = new ArrayList<>();

	private List<Instituicao> instituicoes;
	
	@Inject
	private InstituicaoService instituicaoService;

	@PostConstruct
    public void init(){
        novaInstituicao();
		carregarCidades();
    }

	public void selecionarInstituicao(Instituicao instituicao) {
        setInstituicao(instituicao);

        RequestContext.getCurrentInstance().execute("PF('modalConsultaInstituicao').hide();");
        RequestContext.getCurrentInstance().update("formInstituicao");
    }

    public List<String> getTipos(){
		List<String> tipos = new ArrayList();
		tipos.add("Financeira");
		tipos.add("Educação");
		return tipos;
	}

    private void carregarCidades(){
		cidades = instituicaoService.obterCidades();
	}

	public void consultarInstituicao() {
		instituicoes = instituicaoService.consultaInstituicao(instituicaoDTO);
	}
	
	public void modalConsultaInstituicao() {
		RequestContext.getCurrentInstance().execute("PF('modalConsultaInstituicao').show();");
	}
	
	public void salvarInstituicao() {
		instituicaoService.salvar(instituicao);
		limparCampos();
	}
	
	public void limparCampos() {
		novaInstituicao();
	}
	
	public void novaInstituicao() {
		instituicao = new Instituicao();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public InstituicaoDTO getInstituicaoDTO() {
		return instituicaoDTO;
	}

	public void setInstituicaoDTO(InstituicaoDTO instituicaoDTO) {
		this.instituicaoDTO = instituicaoDTO;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
