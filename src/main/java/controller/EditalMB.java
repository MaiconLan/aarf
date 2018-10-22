package controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

import exception.EstudanteBusinessException;
import org.omnifaces.util.Messages;
import service.EditalService;
import model.Edital;


@ViewScoped
@Named("editalMB")
public class EditalMB implements Serializable {

    private static final long serialVersionUID = 4688373401392205342L;
    private Edital edital;

    @Inject
    private EditalService editalService;

    @PostConstruct
    public void init(){
        novoEdital();
    }

    private void novoEdital(){
        edital = new Edital();
    }

    public void salvarEdital(){
        try {
            editalService.salvarEdital(edital);
            Messages.addInfo(null, "Edital cadastrado com sucesso");

        } catch (Exception e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void finalizarPeriodo(){
        editalService.finalizarPeriodo(edital);
    }

    public boolean isFinalizado(){
        return edital != null && edital.getFinalizado() != null && edital.getFinalizado();
    }

    public boolean renderizarBotaoFinalizar(){
        return edital.getIdEdital() != null && (edital.getFinalizado() == null || !edital.getFinalizado());
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public EditalService getEditalService() {
        return editalService;
    }

    public void setEditalService(EditalService editalService) {
        this.editalService = editalService;
    }
}
