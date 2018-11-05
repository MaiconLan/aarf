package business;

import dao.NoticiaDAO;
import exception.LoginException;
import exception.NoticiaBusinessException;

import model.Noticia;

import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controller.Identity;
@Stateless
public class NoticiaBusiness {
	
	@Inject
	private NoticiaDAO dao;

	@Inject
	private Identity identity;
	
    public void salvarNoticia(Noticia n) throws NoticiaBusinessException, LoginException {

    	n.setAssociado(identity.getUsuario().getAssociado());
    	
        if(n.getIdNoticia() == null)
        	dao.save(n);
        else
        	dao.update(n);
    }

    public void removerNoticia(Noticia n) {
        dao.update(n);
    }

}
