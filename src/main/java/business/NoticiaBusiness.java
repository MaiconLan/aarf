package business;

import dao.NoticiaDAO;
import dto.InstituicaoDTO;
import exception.LoginException;
import exception.NoticiaBusinessException;
import model.Instituicao;
import model.Noticia;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controller.Identity;
@Stateless
public class NoticiaBusiness implements Serializable {

    private static final long serialVersionUID = -2413736911888084396L;

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
    
    public List<Noticia> listarNoticias(Noticia noticia) {
        return dao.consultaNoticia(noticia);
    }

}
