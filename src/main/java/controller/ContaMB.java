package controller;

import exception.ContaBusinessException;
import exception.LoginException;
import model.Conta;
import model.Estudante;
import model.Instituicao;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.ContaService;
import service.EstudanteService;

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
    private ContaService contaService;

    private Conta conta;

    @PostConstruct
    public void init(){
        novaConta();
    }

    public void salvarConta(){
        try {
            contaService.salvarConta(conta);
            Messages.addInfo(null, "Conta salva com sucesso");

        } catch (ContaBusinessException e) {
            e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
        }
    }

    public void inativarConta(){
        try {
            contaService.inativarConta(conta);
            Messages.addWarn(null, "Conta inativada com sucesso");
        } catch (ContaBusinessException e) {
            Messages.addWarn(null, e.getMessage());
        }

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

}
