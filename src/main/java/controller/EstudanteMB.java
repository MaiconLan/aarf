package controller;

import dto.EstudanteDTO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.*;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.CepService;
import service.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("estudanteMB")
public class EstudanteMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Estudante estudante;

    private List<Estudante> listaEstudantes;

    private EstudanteDTO estudanteDTO = new EstudanteDTO();

    private List<Instituicao> instituicoes;

    private boolean alterarLogin;

    @Inject
    private EstudanteService estudanteService;

    @Inject
    private CepService cepService;

    @Inject
    private Identity identity;

    @Inject @Param
    private Long idEstudante;

    private boolean isAcessarPerfil;

    @PostConstruct
    public void init(){
        carregarEstudante();
        isAcessarPerfil = Boolean.valueOf(Faces.getExternalContext().getRequestParameterMap().get("isAcessarPerfil"));
        instituicoes = new ArrayList<>();
    }

    private void carregarEstudante(){
        Estudante estudante = identity.getUsuario().getEstudante();

        if(idEstudante != null) {
            if(identity.isUsuarioEstudante() && estudante.getIdEstudante().equals(idEstudante) || identity.isUsuarioAssociado())
                selecionarEstudante(estudanteService.obterEstudante(idEstudante));

        } else {
            novoEstudante();
        }
    }

    public boolean renderizarCamposAcessarPerfil(){
        return !isAcessarPerfil;
    }

    public void modalConsultaEstudante() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEstudante').show();");
    }

    public void selecionarEstudante(Estudante estudante) {
        setEstudante(estudante);
        if (estudante.getUsuario() == null) {
            estudante.setUsuario(new Usuario());
        }
        if(estudante.getPessoa().getEndereco() == null){
            estudante.getPessoa().setEndereco(new Endereco());
        }

        setAlterarLogin(!estudanteService.isLoginPreenchido(estudante.getUsuario()));

        RequestContext.getCurrentInstance().execute("PF('modalConsultaEstudante').hide();");
        RequestContext.getCurrentInstance().update("formEstudante");
        RequestContext.getCurrentInstance().update("panelUsuario");
    }

    public void salvarEstudante(){
        try {
            estudante.getUsuario().setAlterarLogin(alterarLogin);
            estudanteService.salvarEstudante(estudante);
            Messages.addInfo(null, "Estudante salvo com sucesso");

            if(!isAcessarPerfil)
                novoEstudante();
        } catch (EstudanteBusinessException | LoginException e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void removerEstudante(){
        estudanteService.removerEstudante(estudante);
        Messages.addInfo(null, "Estudante removido com sucesso");
        novoEstudante();
    }

    public void consultarEstudante(){
        listaEstudantes = estudanteService.consultarEstudantes(estudanteDTO);
    }

    public boolean isDesabilitarAlteracaoLogin(){
        return (estudante.getUsuario().getIdUsuario() != null && !alterarLogin);
    }

    public void consultarCep(){
        String cep = estudante.getPessoa().getEndereco().getCep();
        Long idEndereco = estudante.getPessoa().getEndereco().getIdEndereco();
        if(cep != null && !cep.isEmpty()){
            Endereco endereco = cepService.getEnderecoCompleto(cep);
            endereco.setIdEndereco(idEndereco);
            endereco.setPessoa(estudante.getPessoa());
            estudante.getPessoa().setEndereco(endereco);
        }
    }

    public void toggleAlterarLogin(){
        setAlterarLogin(!alterarLogin);
    }

    public boolean renderizaAlterarLogin(){
        return estudante.getIdEstudante() != null;
    }

    private void novoEstudante(){
        estudante = new Estudante();
        setAlterarLogin(true);
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public EstudanteDTO getEstudanteDTO() {
        return estudanteDTO;
    }

    public void setEstudanteDTO(EstudanteDTO estudanteDTO) {
        this.estudanteDTO = estudanteDTO;
    }

    public List<Estudante> getListaEstudantes() {
        return listaEstudantes;
    }

    public void setListaEstudantes(List<Estudante> listaEstudantes) {
        this.listaEstudantes = listaEstudantes;
    }

    public boolean isAlterarLogin() {
        return alterarLogin;
    }

    public void setAlterarLogin(boolean alterarLogin) {
        this.alterarLogin = alterarLogin;
    }

}
