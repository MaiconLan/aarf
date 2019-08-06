package controller;

import business.InstituicaoBusiness;
import business.PrestacaoContaBusiness;
import model.Instituicao;
import model.Pagamento;
import model.PrestacaoConta;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Instituicao instituicao;
    private List<Instituicao> instituicoes;
    private PrestacaoConta prestacaoConta;

    private Pagamento pagamento;

    @PostConstruct
    public void init() {
        novaPrestacao();
        novoPagamento();
        carregarInstituicoes();
    }

    private void novaPrestacao() {
        prestacaoConta = new PrestacaoConta();
    }
    private void novoPagamento() {
        pagamento = new Pagamento();
    }

    public void salvar() {
        try {
            prestacaoContaBusiness.salvarPrestacao(prestacaoConta);
            Messages.addInfo(null, "Prestação realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarPagamento() {
        prestacaoConta.getPagamentos().add(pagamento);
        novoPagamento();
    }

    public void removerPagamento(Pagamento pagamento){
        prestacaoConta.getPagamentos().remove(pagamento);
    }

    public void editarPagamento(Pagamento pagamento){
        setPagamento(pagamento);
    }

    public void enviarArquivo(FileUploadEvent event) {
        if (prestacaoContaAnexoMB.getPrestacaoConta() == null)
            prestacaoContaAnexoMB.setPrestacaoConta(prestacaoConta);

        prestacaoContaAnexoMB.enviarArquivoTemporario(event);
    }

    private void carregarInstituicoes() {
        instituicoes = instituicaoBusiness.obterInstuicoesFinanceiras();
    }

    public PrestacaoConta getPrestacaoConta() {
        return prestacaoConta;
    }

    public void setPrestacaoConta(PrestacaoConta prestacaoConta) {
        this.prestacaoConta = prestacaoConta;
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

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}
