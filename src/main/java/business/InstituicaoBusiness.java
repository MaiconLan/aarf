package business;

import dao.CidadeDAO;
import dao.InstituicaoDAO;
import dto.InstituicaoDTO;
import model.Cidade;
import model.Instituicao;

import javax.inject.Inject;
import java.util.List;

public class InstituicaoBusiness {

	@Inject
	private InstituicaoDAO instituicaoDAO;
	
	@Inject
	private CidadeDAO cidadeDAO;
	
	public void salvar(Instituicao instituicao) {
		if(instituicao.getIdInstituicao() == null)
			instituicaoDAO.save(instituicao);
		else
			instituicaoDAO.update(instituicao);
	}
	
	 public List<Instituicao> consultarInstituicoes(InstituicaoDTO instituicaoDTO) {
	        return instituicaoDAO.consultarInstituicoes(instituicaoDTO);
	 }
	 
	 public List<Instituicao> listarInstituicoes() {
	        return instituicaoDAO.listarInstituicoes();
	 }
	

    public List<Instituicao> obterInstituicoes() {
		return instituicaoDAO.obterInstituicoes();
    }

    public List<Cidade> obterCidades() {
		return cidadeDAO.list();
    }

	public List<Instituicao> obterInstuicoesFinanceiras() {
		return instituicaoDAO.obterInstituicoesFinanceiras();
	}

	public List<Instituicao> obterInstituicoesEnsino() {
		return instituicaoDAO.obterInstituicoesEnsino();
	}
}
