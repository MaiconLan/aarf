package service;

import exception.LoginException;
import exception.NoticiaBusinessException;
import model.Noticia;

import java.io.Serializable;
import java.util.List;

public interface NoticiaService extends Serializable {

    void salvarNoticia(Noticia n) throws NoticiaBusinessException, LoginException;

    void removerNoticia(Noticia n);
    
    List<Noticia> listarNoticias();
}
