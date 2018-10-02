package service;

import javax.inject.Inject;

import business.InstituicaoBusiness;
import model.Instituicao;

public class InstituicaoServiceImpl implements InstituicaoService{

	@Inject
	private InstituicaoBusiness instituicaoBusiness;
	
	@Override
	public void salvar(Instituicao instituicao) {
		instituicaoBusiness.salvar(instituicao);
	}

	
	
}
