package controller;

import business.EstudanteBusiness;
import business.InstituicaoBusiness;
import dto.EstudanteDTO;
import exception.CepBussinesException;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Endereco;
import model.Estudante;
import model.Instituicao;
import model.Usuario;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.CepService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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
    private EstudanteBusiness estudanteBusiness;

    @Inject
    private CepService cepService;

    @Inject
    private Identity identity;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject @Param
    private Long idEstudante;

    private boolean isAcessarPerfil;

    @PostConstruct
    public void init(){
        carregarEstudante();
        isAcessarPerfil = Boolean.valueOf(Faces.getExternalContext().getRequestParameterMap().get("isAcessarPerfil"));
        carregarInstituicoes();
    }

    private void carregarInstituicoes(){
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
    }

    private void carregarEstudante(){
        Estudante estudante = identity.getUsuario().getEstudante();

        if(idEstudante != null) {
            if(identity.isUsuarioEstudante() && estudante.getIdEstudante().equals(idEstudante) || identity.isUsuarioAssociado())
                selecionarEstudante(estudanteBusiness.obterEstudante(idEstudante));

        } else {
            novoEstudante();
        }
    }

    public boolean renderizarCamposAcessarPerfil(){
        return !isAcessarPerfil;
    }

    public boolean renderizarCampoRemoverEstudante(){
        return renderizarCamposAcessarPerfil() && estudante.getIdEstudante() != null;
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

        setAlterarLogin(!estudanteBusiness.isLoginPreenchido(estudante.getUsuario()));

        RequestContext.getCurrentInstance().execute("PF('modalConsultaEstudante').hide();");
        RequestContext.getCurrentInstance().update("formEstudante");
        RequestContext.getCurrentInstance().update("panelUsuario");
    }

    public void salvarEstudante(){
        try {
            estudante.getUsuario().setAlterarLogin(alterarLogin);
            estudanteBusiness.salvarEstudante(estudante);
            Messages.addInfo(null, "Estudante salvo com sucesso");

            if(!isAcessarPerfil)
                novoEstudante();
        } catch (EstudanteBusinessException | LoginException e) {
            e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
            e.printStackTrace();
        }
    }

    public void removerEstudante(){
        estudanteBusiness.removerEstudante(estudante);
        Messages.addInfo(null, "Estudante removido com sucesso");
        novoEstudante();
    }

    public void consultarEstudante(){
        listaEstudantes = estudanteBusiness.consultarEstudantes(estudanteDTO);
    }

    public boolean isDesabilitarAlteracaoLogin(){
        return (estudante.getUsuario().getIdUsuario() != null && !alterarLogin);
    }

    public void consultarCep(){
        String cep = estudante.getPessoa().getEndereco().getCep();
        Long idEndereco = estudante.getPessoa().getEndereco().getIdEndereco();
        if(cep != null && !cep.isEmpty()){
            try {
                Endereco endereco = cepService.getEnderecoCompleto(cep);
                endereco.setIdEndereco(idEndereco);
                endereco.setPessoa(estudante.getPessoa());
                estudante.getPessoa().setEndereco(endereco);
            } catch (CepBussinesException e) {
                Messages.addWarn(null, e.getMessage());
                e.printStackTrace();
            }

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
