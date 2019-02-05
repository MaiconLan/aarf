package controller;

import model.Atualizacao;
import service.AtualizacaoService;
import utils.DateUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@ViewScoped
@Named("avisoAtualizacaoMB")
public class AvisoAtualizacaoMB implements Serializable {

    private static final long serialVersionUID = -5319505388371020513L;

    private boolean possuiAtualizacaoPendente = false;

    @Inject
    private AtualizacaoService atualizacaoService;

    private Atualizacao atualizacao;

    @PostConstruct
    public void init(){
        atualizacao = new Atualizacao();
        verificarAtualizacaoPendente();
    }

    public String horarioPrevista(){
        String horario = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(DateUtils.FORMATO_DIA_MES_ANO);
        if(atualizacao.getIdAtualizacao() != null) {
            horario += DateUtils.formatoDataHora(atualizacao.getInicio()) + " até " + DateUtils.formatoDataHora(atualizacao.getTermino());
        }
        return horario;
    }

    public void concluir(){
        atualizacaoService.concluir(atualizacao);
    }

    private void verificarAtualizacaoPendente(){
        possuiAtualizacaoPendente = atualizacaoService.possuiAtualizacaoPendente();

        if(possuiAtualizacaoPendente)
            atualizacao = atualizacaoService.obterAtualizacaoPendente();
    }

    public boolean isPossuiAtualizacaoPendente() {
        return possuiAtualizacaoPendente;
    }

    public void setPossuiAtualizacaoPendente(boolean possuiAtualizacaoPendente) {
        this.possuiAtualizacaoPendente = possuiAtualizacaoPendente;
    }

    public Atualizacao getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Atualizacao atualizacao) {
        this.atualizacao = atualizacao;
    }
}
