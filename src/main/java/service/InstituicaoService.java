package service;

import java.util.List;

import dto.InstituicaoDTO;
import model.Cidade;
import model.Instituicao;

public interface InstituicaoService {

	void salvar(Instituicao instituicao);
	
	List<Instituicao> consultaInstituicao(InstituicaoDTO instituicaoDTO);
	
	List<Instituicao> listarInstituicao();
	

	List<Instituicao> obterInstituicoes();

	List<Instituicao> obterInstituicoesEnsino();

	List<Instituicao> obterInstuicoesFinanceiras();

    List<Cidade> obterCidades();
}
