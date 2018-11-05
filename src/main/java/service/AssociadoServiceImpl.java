package service;

import business.AssociadoBusiness;
import dao.AssociadoDAO;
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
    private AssociadoBusiness associadoBusiness;

    @Inject
    private AssociadoDAO associadoDAO;

    @Override
    public void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException {
        associadoBusiness.salvarAssociado(associado);
    }

    @Override
    public Associado obterAssociado(Long idEstudante) {
        return associadoDAO.findById(idEstudante);
    }


    @Override
    public List<Associado> consultarAssociados(AssociadoDTO associadoDTO) {
        return associadoBusiness.consultarAssociados(associadoDTO);
    }

    @Override
    public boolean isLoginPreenchido(Usuario usuario) {
        return associadoBusiness.isLoginPreenchido(usuario);
    }

    @Override
    public void removerAssociado(Associado a) {
        associadoBusiness.removerAssociado(a);
    }
}

