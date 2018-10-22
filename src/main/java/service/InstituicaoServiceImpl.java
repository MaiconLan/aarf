package service;

import java.io.Serializable;

import javax.inject.Inject;

import business.InstituicaoBusiness;
import model.Instituicao;

public class InstituicaoServiceImpl implements InstituicaoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -308905687182900333L;
	
	
	@Inject
	private InstituicaoBusiness instituicaoBusiness;
	
	@Override
	public void salvar(Instituicao instituicao) {
		instituicaoBusiness.salvar(instituicao);
	}

	
	
}
