package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import business.InstituicaoBusiness;
import dto.InstituicaoDTO;
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

	@Override
	public List<Instituicao> consultaInstituicao(InstituicaoDTO instituicaoDTO) {
		return instituicaoBusiness.consultarInstituicoes(instituicaoDTO);
	}

	@Override
	public List<Instituicao> listarInstituicao() {
		return instituicaoBusiness.listarInstituicoes();
	}

	
	
}
