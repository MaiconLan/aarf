package controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

import business.EditalBusiness;
import dao.EditalDAO;
import dto.EditalDTO;
import exception.EstudanteBusinessException;
import model.Endereco;
import model.Estudante;
import model.Usuario;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.EditalService;
import model.Edital;

import java.util.List;
import javax.faces.model.SelectItem;

@ViewScoped
@Named("editalMB")
public class EditalMB implements Serializable {

    private static final long serialVersionUID = 4688373401392205342L;
    private Edital edital;

    private EditalDTO editalDTO = new EditalDTO();

    @Inject
    private EditalService editalService;

    private EditalBusiness editalBusiness;

    private List<Edital> editais;

    @PostConstruct
    public void init() {
        novoEdital();
    }

    private void novoEdital() {
        edital = new Edital();
    }

    public void salvarEdital() {
        try {
            editalService.salvarEdital(edital);
            Messages.addInfo(null, "Edital cadastrado com sucesso");

        } catch (Exception e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void selecionarEditais(Edital edital) {
        setEdital(edital);
        RequestContext.getCurrentInstance().execute("PF('modalFinalizarEdital').hide();");
        RequestContext.getCurrentInstance().update("formEdital");
    }

    public void modalEdital() {
        RequestContext.getCurrentInstance().execute("PF('modalFinalizarEdital').show();");
    }

    public void consultarEdital() {
         editais = editalService.consultarEdital(editalDTO);
    }


    public void finalizarPeriodo() {
        editalService.finalizarPeriodo(edital);
    }

    public boolean isFinalizado() {
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

    public EditalDTO getEditalDTO() {
        return editalDTO;
    }

    public void setEditalDTO(EditalDTO editalDTO) {
        this.editalDTO = editalDTO;
    }

    public List<Edital> getEditais() {
        return editais;
    }

    public void setEditais(List<Edital> editais) {
        this.editais = editais;
    }
}
