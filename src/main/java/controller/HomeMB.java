package controller;

import service.NoticiaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Noticia;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named(value = "homeMB")
public class HomeMB  implements Serializable {
    private static final long serialVersionUID = -7785394172005232068L;

    @Inject
    private NoticiaService noticiaService;
    
    private List<Noticia> noticias;
    
    @PostConstruct
    public void init() {
    	carregarNoticias();
    }
    
    private void carregarNoticias() {
    	noticias = noticiaService.listarNoticias();
    }

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
    
}
