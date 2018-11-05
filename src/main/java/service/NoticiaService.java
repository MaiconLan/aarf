package service;

import exception.LoginException;
import exception.NoticiaBusinessException;
import model.Noticia;

import java.io.Serializable;

public interface NoticiaService extends Serializable {

    void salvarNoticia(Noticia n) throws NoticiaBusinessException, LoginException;

    void removerNoticia(Noticia n);
}
