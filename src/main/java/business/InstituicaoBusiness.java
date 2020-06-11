package business;

import dao.CidadeDAO;
import dao.InstituicaoDAO;
import dto.InstituicaoDTO;
import exception.InstituicaoBusinessException;
import model.Cidade;
import model.Instituicao;
import utils.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class InstituicaoBusiness implements Serializable {

	private static final long serialVersionUID = -8728285264514923175L;

	@Inject
	private InstituicaoDAO instituicaoDAO;
	
	@Inject
	private CidadeDAO cidadeDAO;
	
	public void salvar(Instituicao instituicao) throws InstituicaoBusinessException {
		validarInstituicao(instituicao);

		instituicaoDAO.save(instituicao);
	}

	private void validarInstituicao(Instituicao instituicao) throws InstituicaoBusinessException {
		if(StringUtils.isBlank(instituicao.getTipo()))
			throw new InstituicaoBusinessException("Tipo é obrigatório");
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

    public Instituicao obterInstituicao(Long idInstituicao) {
		return instituicaoDAO.findById(idInstituicao);
    }
}
