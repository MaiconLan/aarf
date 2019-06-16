package controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import business.EditalBusiness;
import dto.EditalDTO;
import model.*;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import utils.DateUtils;

import java.util.List;

@ViewScoped
@Named("editalMB")
public class EditalMB implements Serializable {

    private static final long serialVersionUID = 4688373401392205342L;
    private Edital edital;

    private EditalDTO editalDTO = new EditalDTO();

    @Inject
    private EditalBusiness editalBusiness;

    @Inject @Param
    private Long idEdital;

    private List<Edital> editais;

    @PostConstruct
    public void init() {
        if(idEdital != null)
            carregarEdital();
        else
            novoEdital();
    }

    private void novoEdital() {
        edital = new Edital();
    }

    private void carregarEdital(){
        edital = editalBusiness.listarEdital(idEdital);
    }

    public void salvarEdital() {
        try {
            editalBusiness.salvarEdital(edital);
            Messages.addInfo(null, "Edital cadastrado com sucesso");

        } catch (Exception e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void selecionarEditais(Edital edital) {
        setEdital(edital);
        RequestContext.getCurrentInstance().execute("PF('modalFinalizarEdital').hide();");
        RequestContext.getCurrentInstance().update("formEdital");
        RequestContext.getCurrentInstance().update("formInscritos");
    }

    public void modalEdital() {
        RequestContext.getCurrentInstance().execute("PF('modalFinalizarEdital').show();");
    }

    public void consultarEdital() {
         editais = editalBusiness.consultarEdital(editalDTO);
    }

    public String dataFormatada(LocalDateTime localDateTime){
        return DateUtils.formatoDataHora(localDateTime);
    }

    public void finalizarPeriodo() {
        editalBusiness.finalizarPeriodo(edital);
    }

    public boolean isFinalizado() {
        return edital != null && edital.getFinalizado() != null && edital.getFinalizado();
    }

    public boolean renderizarBotaoFinalizar(){
        return edital.getIdEdital() != null && (edital.getFinalizado() == null || !edital.getFinalizado());
    }

    public boolean renderizaInscritos(){
        return edital != null && edital.getIdEdital() != null;
    }

    public void acessarEstudante(Matricula matricula) {
        Estudante estudante = matricula.getEstudante();
        try {
            Faces.redirect("security/cadastros/estudante/cadastro-estudante.xhtml?idEstudante=" + estudante.getIdEstudante() + "&isAcessarPerfil=false");

        } catch (IOException e) {
            e.printStackTrace();
            Messages.addError(null, "Erro ao redirecionar para o cadastro do estudante!");
        }
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
