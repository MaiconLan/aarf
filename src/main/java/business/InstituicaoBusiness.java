package business;

import java.util.List;

import javax.inject.Inject;

import dao.InstituicaoDAO;
import dto.EstudanteDTO;
import dto.InstituicaoDTO;
import model.Estudante;
import model.Instituicao;
import model.Pessoa;
import utils.StringUtils;

public class InstituicaoBusiness {

	@Inject
	private InstituicaoDAO instituicaoDAO;
	
	public void salvar(Instituicao instituicao) {
		if(instituicao.getIdInstituicao() == null)
			instituicaoDAO.save(instituicao);
		else
			instituicaoDAO.update(instituicao);
	}
	
	 public List<Instituicao> consultarInstituicoes(InstituicaoDTO instituicaoDTO) {
	        return instituicaoDAO.consultarInstituicoes(instituicaoDTO);
	 }
	
}
