package controller;

import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Endereco;
import model.Estudante;
import model.Instituicao;
import org.omnifaces.util.Messages;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;
import service.CepService;
import service.EstudanteService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named("estudanteMB")
public class EstudanteMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Estudante estudante;

    private List<Instituicao> instituicoes;

    @Inject
    private EstudanteService estudanteService;

    @Inject
    private CepService cepService;

    @PostConstruct
    public void init(){
        novoEstudante();
        instituicoes = new ArrayList<>();
    }

    public void modalConsultaEstudante() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEstudante').show();");
    }

    public void salvarEstudante(){
        try {
            estudanteService.salvarEstudante(estudante);
            Messages.addInfo(null, "Estudante salvo com sucesso");

        } catch (EstudanteBusinessException | LoginException e) {
            e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));
        }
    }

    public void excluirEstudante(){
        Messages.addWarn(null, "Estudante excluído com sucesso");
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

    private void novoEstudante(){
        estudante = new Estudante();
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
}
