package controller;

import exception.LoginException;
import exception.NoticiaBusinessException;

import model.Instituicao;
import model.Noticia;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import enumered.CargoEnum;
import enumered.SerevidadeEnum;
import service.InstituicaoService;
import service.NoticiaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named("noticiaMB")
public class NoticiaMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Noticia noticia;

    private List<Instituicao> instituicoes;
    
    @Inject
    private InstituicaoService instituicaoService;
    
    @Inject
    private NoticiaService service;

    @PostConstruct
    public void init(){
        novaNoticia();
        carregarInstituicoes();
    }

    public void selecionarNoticia(Noticia noticia) {
        novaNoticia();
        if (noticia != null && noticia.getIdNoticia() != null) {
        	this.noticia = noticia; 
        }

        RequestContext.getCurrentInstance().update("form");
    }

    public void salvarNoticia(){
        try {
        	noticia.setPublicacao(LocalDateTime.now());
            service.salvarNoticia(noticia);
            Messages.addInfo(null, "Notícia salva com sucesso");
            novaNoticia();
        } catch (NoticiaBusinessException | LoginException e) {
            Messages.addError(null, "Erro no Cadastro");
        }
    }

    public void removerNoticia(){
        service.removerNoticia(noticia);
        Messages.addInfo(null, "Noticia removido com sucesso");
        novaNoticia();
    }

    private void carregarInstituicoes(){
        instituicoes = instituicaoService.obterInstituicoesEnsino();
    }
    
    public  SerevidadeEnum[] getSerevidades(){
		return SerevidadeEnum.values();
		
	}
    
    private void novaNoticia(){
        noticia = new Noticia();
    }

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}
}
