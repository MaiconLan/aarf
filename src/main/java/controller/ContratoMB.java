package controller;

import business.ContratoBusiness;
import exception.ContratoBusinessException;
import model.Contrato;
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
@Named("contratoMB")
public class ContratoMB implements Serializable {

    private static final long serialVersionUID = -4003034012569625444L;

    @Inject
    private ContratoBusiness contratoBusiness;

    private List<Contrato> contratos = new ArrayList<>();

    private Contrato contrato;

    @PostConstruct
    public void init() {
        contrato = new Contrato();
    }

    public void modalContrato() {
        RequestContext.getCurrentInstance().execute("PF('modalBuscaContrato').show();");
    }

    public void salvar() {
        try {
            contratoBusiness.salvar(contrato);
            Messages.addInfo(null, "Contrato adicionado com sucesso!");
        } catch (ContratoBusinessException e) {
            e.printStackTrace();
            Messages.addError(null, e.getMessage());
        }
    }

    public void consultarContrato() {
        contratos = contratoBusiness.consultarContratos();
    }

    public void selecionarContrato(Contrato contrato){
        setContrato(contrato);
        RequestContext.getCurrentInstance().execute("PF('modalBuscaContrato').hide();");
        RequestContext.getCurrentInstance().update("formContrato");
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public ContratoBusiness getContratoBusiness() {
        return contratoBusiness;
    }

    public void setContratoBusiness(ContratoBusiness contratoBusiness) {
        this.contratoBusiness = contratoBusiness;
    }
}
