package controller;

import business.InstituicaoBusiness;
import business.PrestacaoContaBusiness;
import dto.InstituicaoDTO;
import dto.PrestacaoContaDTO;
import model.Instituicao;
import model.PrestacaoConta;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("prestacaoContaMB")
public class PrestacaoContaMB implements Serializable {

    private static final long serialVersionUID = 5913546911158814932L;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject
    private PrestacaoContaBusiness prestacaoContaBusiness;

    @Inject
    private PrestacaoContaAnexoMB prestacaoContaAnexoMB;

    private InstituicaoDTO instituicaoDTO;

    private PrestacaoContaDTO prestacaoContaDTO;

    private Instituicao instituicao;
    private List<PrestacaoConta> prestacoes;
    private List<Instituicao> instituicoes;
    private PrestacaoConta prestacaoConta;

    @PostConstruct
    public void init(){
        instituicaoDTO = new InstituicaoDTO();
        newPrestacaoConta();
        carregarInstituicoes();
    }

    private void newPrestacaoConta(){
        prestacaoConta = new PrestacaoConta();
    }

    public void salvar(){
        try{
            prestacaoContaBusiness.salvarPrestacao(prestacaoConta);
            Messages.addInfo(null, "Prestação realizada com sucesso");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarArquivo(FileUploadEvent event) {
        if(prestacaoContaAnexoMB.getPrestacaoConta() == null)
            prestacaoContaAnexoMB.setPrestacaoConta(prestacaoConta);

        prestacaoContaAnexoMB.enviarArquivoTemporario(event);
    }

    private void carregarInstituicoes(){
        instituicoes = instituicaoBusiness.obterInstuicoesFinanceiras();
    }

    public PrestacaoConta getPrestacaoConta() {
        return prestacaoConta;
    }

    public void setPrestacaoConta(PrestacaoConta prestacaoConta) {
        this.prestacaoConta = prestacaoConta;
    }

    public List<PrestacaoConta> getPrestacoes() {
        return prestacoes;
    }

    public void setPrestacoes(List<PrestacaoConta> prestacoes) {
        this.prestacoes = prestacoes;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }
    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }
}
