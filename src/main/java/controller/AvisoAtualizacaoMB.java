package controller;

import business.AtualizacaoBusiness;
import model.Atualizacao;
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
    private AtualizacaoBusiness atualizacaoBusiness;

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
            horario += DateUtils.formatoData(atualizacao.getInicio()) + " até " + DateUtils.formatoData(atualizacao.getTermino());
        }
        return horario;
    }

    public void concluir(){
        atualizacaoBusiness.concluir(atualizacao);
    }

    private void verificarAtualizacaoPendente(){
        possuiAtualizacaoPendente = atualizacaoBusiness.possuiAtualizacaoPendente();

        if(possuiAtualizacaoPendente)
            atualizacao = atualizacaoBusiness.obterAtualizacaoPendente();
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
