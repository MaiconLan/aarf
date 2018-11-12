package service;

import business.NoticiaBusiness;
import exception.LoginException;
import exception.NoticiaBusinessException;
import model.Noticia;

import java.util.List;

import javax.inject.Inject;


public class NoticiaServiceImpl implements NoticiaService {

    private static final long serialVersionUID = 1830819295361181977L;

    @Inject
    private NoticiaBusiness noticiaBusiness;

	@Override
	public void salvarNoticia(Noticia n) throws NoticiaBusinessException, LoginException {
		noticiaBusiness.salvarNoticia(n);
	}

	@Override
	public void removerNoticia(Noticia n) {
		noticiaBusiness.removerNoticia(n);
	}

	@Override
	public List<Noticia> listarNoticias() {
		// TODO Auto-generated method stub
		return null;
	}

}

