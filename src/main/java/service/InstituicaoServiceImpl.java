package service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import business.InstituicaoBusiness;
import dao.CidadeDAO;
import dao.InstituicaoDAO;
import dto.InstituicaoDTO;
import model.Cidade;
import model.Instituicao;

public class InstituicaoServiceImpl implements InstituicaoService, Serializable {

	private static final long serialVersionUID = -308905687182900333L;

	@Inject
	private InstituicaoBusiness instituicaoBusiness;

	@Inject
	private InstituicaoDAO instituicaoDAO;

	@Inject
	private CidadeDAO cidadeDAO;

	@Override
	public void salvar(Instituicao instituicao) {
		instituicaoBusiness.salvar(instituicao);
	}

	@Override
	public List<Instituicao> consultaInstituicao(InstituicaoDTO instituicaoDTO) {
		return instituicaoBusiness.consultarInstituicoes(instituicaoDTO);
	}

	@Override
	public List<Instituicao> obterInstituicoes() {
		return instituicaoDAO.obterInstituicoes();
	}

	@Override
	public List<Instituicao> obterInstituicoesEnsino() {
		return instituicaoDAO.obterInstituicoesEnsino();
	}

	@Override
	public List<Cidade> obterCidades() {
		return cidadeDAO.list();
	}


}
