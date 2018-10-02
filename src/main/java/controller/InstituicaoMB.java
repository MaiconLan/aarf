package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Instituicao;
import service.InstituicaoService;

@ViewScoped
@Named("instituicaoMB")
public class InstituicaoMB implements Serializable{

	private static final long serialVersionUID = 1L;

	private Instituicao instituicao;
	@Inject
	private InstituicaoService instituicaoService;
	
	@PostConstruct
    public void init(){
        novaInstituicao();
    }
	
	public void salvarInstituicao() {
		instituicaoService.salvar(instituicao);
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
	
}
