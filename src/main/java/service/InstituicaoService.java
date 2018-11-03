package service;

import java.util.List;

import dto.InstituicaoDTO;
import model.Instituicao;

public interface InstituicaoService {

	void salvar(Instituicao instituicao);
	
	List<Instituicao> consultaInstituicao(InstituicaoDTO instituicaoDTO);

	List<Instituicao> obterInstituicoes();

	List<Instituicao> obterInstituicoesEnsino();

}
