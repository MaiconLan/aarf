package service;

import business.AssociadoBusiness;
import dto.AssociadoDTO;
import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Usuario;

import javax.inject.Inject;
import java.util.List;

public class AssociadoServiceImpl implements AssociadoService {

    private static final long serialVersionUID = 1830819295361181977L;

    @Inject
    private AssociadoBusiness assocaidoBusiness;

    @Override
    public void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException {
    	assocaidoBusiness.salvarAssociado(associado);
    }

    @Override
    public Associado obterAssociado(Long idEstudante) {
        return null;
    }


    @Override
    public List<Associado> consultarAssociados(AssociadoDTO associadoDTO) {
        return assocaidoBusiness.consultarAssociados(associadoDTO);
    }

    @Override
    public boolean isLoginPreenchido(Usuario usuario) {
        return assocaidoBusiness.isLoginPreenchido(usuario);
    }

    @Override
    public void removerAssociado(Associado a) {
        assocaidoBusiness.removerAssociado(a);
    }
}

