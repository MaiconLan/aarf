package controller;

import business.PrestacaoContaBusiness;
import dao.InstituicaoDAO;
import dto.InstituicaoDTO;
import dto.PrestacaoContaDTO;
import model.Instituicao;
import model.PrestacaoConta;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import service.InstituicaoService;
import service.PrestacaoContaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

@ViewScoped
@Named("prestacaoContaMB")
public class PrestacaoContaMB implements Serializable {

    private static final long serialVersionUID = 5913546911158814932L;
    private PrestacaoConta prestacaoConta;
    @Inject
    private InstituicaoService instituicaoService;
    private PrestacaoContaDTO prestacaoContaDTO = new PrestacaoContaDTO();
    @Inject
    private PrestacaoContaService prestacaoContaService;
    @Inject
    private InstituicaoDTO instituicaoDTO;

    private Instituicao instituicao;
    private PrestacaoContaBusiness prestacaoContaBusiness;
    private List<PrestacaoConta> prestacoes;
    private List<Instituicao> instituicoes;
    private PrestacaoContaAnexoMB prestacaoContaAnexoMB;

    @PostConstruct
    public void init(){
        newPrestacaoConta();
        carregarInstituicoes();
        carregarPrestacoes();
    }

    private void newPrestacaoConta(){
        prestacaoConta = new PrestacaoConta();
    }

    public void salvar(){
        try{
            prestacaoContaService.salvarPrestacao(prestacaoConta);
            Messages.addInfo(null, "Prestação realizada com sucesso");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarArquivo(FileUploadEvent event) {
        if(prestacaoContaAnexoMB.getPrestacaoConta() == null)
            prestacaoContaAnexoMB.setPrestacaoConta(prestacaoConta);

        prestacaoContaAnexoMB.enviarArquivoTemporario(event);


        byte foto[] = event.getFile().getContents();
        String encoded = Base64.getEncoder().encodeToString(foto);
        prestacaoContaAnexoMB.salvarAnexos(encoded, event.getFile().getFileName());

    }

    private void carregarPrestacoes(){
        prestacoes = prestacaoContaService.obterPrestacao();
    }


    private void carregarInstituicoes(){
        instituicoes = instituicaoService.obterInstuicoesFinanceiras();
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
