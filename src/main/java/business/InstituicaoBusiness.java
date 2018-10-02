package business;

import javax.inject.Inject;

import dao.InstituicaoDAO;
import model.Instituicao;

public class InstituicaoBusiness {

	@Inject
	private InstituicaoDAO instituicaoDAO;
	
	public void salvar(Instituicao instituicao) {
		if(instituicao.getIdInstituicao() == null)
			instituicaoDAO.save(instituicao);
		else
			instituicaoDAO.update(instituicao);
	}
	
}
