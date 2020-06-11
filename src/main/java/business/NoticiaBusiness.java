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
        dao.save(n);
    }

    public void removerNoticia(Noticia n) {
        dao.remove(n.getIdNoticia());
    }
    
    public List<Noticia> listarNoticias(Noticia noticia) {
        return dao.consultaNoticia(noticia);
    }

}
