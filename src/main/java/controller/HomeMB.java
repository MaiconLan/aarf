package controller;

import business.NoticiaBusiness;
import model.Noticia;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ViewScoped
@Named("homeMB")
public class HomeMB  implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Inject
    private NoticiaBusiness noticiaBusiness;
    
    private Noticia noticia;
    
    private List<Noticia> noticias = new ArrayList<Noticia>();
    
    @PostConstruct
    public void init() {
    	this.noticia = new Noticia();
    	carregarNoticias();
    	
    }
    
    private void carregarNoticias() {
    	noticias = noticiaBusiness.listarNoticias(this.noticia);
    }

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	} 
	
}
