package service;

import business.EstudanteBusiness;
import exception.AssociadoBusinessException;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Associado;
import model.Estudante;

import javax.inject.Inject;
import java.util.List;

public class AssociadoServiceImpl implements AssociadoService {

    private static final long serialVersionUID = 1830819295361181977L;

	@Override
	public void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Estudante obterEstudante(Long idAssociado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estudante> consultarAssociadoPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
