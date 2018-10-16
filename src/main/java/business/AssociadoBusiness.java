package business;

import dao.AssociadoDAO;
import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;

import service.UsuarioService;


import javax.ejb.Stateless;
import javax.inject.Inject;
@Stateless
public class AssociadoBusiness {

    @Inject
    private AssociadoDAO dao;

    @Inject
    private UsuarioService usuarioService;

    public void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException {
        usuarioService.salvarUsuario(associado.getUsuario());
        dao.save(associado);
    }

}
