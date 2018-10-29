package controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

import business.EditalBusiness;
import dao.EditalDAO;
import exception.EstudanteBusinessException;
import org.omnifaces.util.Messages;
import service.EditalService;
import model.Edital;
import java.util.List;
import javax.faces.model.SelectItem;

@ViewScoped
@Named("editalMB")
public class EditalMB implements Serializable {

    private static final long serialVersionUID = 4688373401392205342L;
    private Edital edital;
    private List<Edital> editais;

    @Inject
    private EditalService editalService;

    private EditalBusiness editalBusiness;

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

    public void selectEditais(){

    }


    public void finalizarPeriodo(){
        editalService.finalizarPeriodo(edital);
    }

    public boolean isFinalizado(){
        return edital != null && edital.getFinalizado() != null && edital.getFinalizado();
    }


    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }



}
