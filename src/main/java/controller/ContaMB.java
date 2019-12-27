package controller;

import business.BancoBusiness;
import business.ContaBusiness;
import dto.ContaDTO;
import exception.ContaBusinessException;
import model.Banco;
import model.Conta;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("contaMB")
public class ContaMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    @Inject
    private ContaBusiness contaBusiness;

    @Inject
    private BancoBusiness bancoBusiness;

    private Conta conta;

    private ContaDTO contaDTO = new ContaDTO();

    private List<Conta> listaContas = new ArrayList<>();

    private List<Banco> listaBancos = new ArrayList<>();

    @PostConstruct
    public void init(){
        novaConta();
        listarBancos();
    }

    public void salvarConta(){
        try {
            contaBusiness.salvarConta(conta);
            Messages.addInfo(null, "Conta salva com sucesso");
            novaConta();
        } catch (ContaBusinessException e) {
            e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
        }
    }

    public void inativarConta(){
        try {
            contaBusiness.inativarConta(conta);
            Messages.addWarn(null, "Conta inativada com sucesso");
        } catch (ContaBusinessException e) {
            e.getMessages().forEach(error -> Messages.addError(null, error));
        }

    }

    public void consultarConta(){
        listaContas = contaBusiness.consultarConta(contaDTO);

        if(listaContas.isEmpty())
            Messages.addWarn(null, "Não foram encontrados resultados.");
    }

    public void listarBancos(){
        listaBancos = bancoBusiness.listarBancos();

        if(listaContas.isEmpty())
            Messages.addWarn(null, "Não foram encontrados resultados.");
    }

    public void selecionarConta(Conta conta) {
        setConta(conta);

        RequestContext.getCurrentInstance().execute("PF('modalConsultaConta').hide();");
        RequestContext.getCurrentInstance().update("formConta");
    }

    public void modalConsultaConta() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaConta').show();");
    }

    private void novaConta(){
        conta = new Conta();
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public ContaDTO getContaDTO() {
        return contaDTO;
    }

    public void setContaDTO(ContaDTO contaDTO) {
        this.contaDTO = contaDTO;
    }

    public List<Conta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Conta> listaContas) {
        this.listaContas = listaContas;
    }

    public List<Banco> getListaBancos() {
        return listaBancos;
    }

    public void setListaBancos(List<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }
}
