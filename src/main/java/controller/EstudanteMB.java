package controller;

import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Estudante;
import model.Instituicao;
import org.omnifaces.util.Messages;
import service.EstudanteService;

import javax.annotation.PostConstruct;
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

    private List<Instituicao> instituicoes;

    @Inject
    private EstudanteService estudanteService;

    @PostConstruct
    public void init(){
        novoEstudante();
        instituicoes = new ArrayList<>();
    }

    public void salvarEstudante(){
        try {
            estudanteService.salvarEstudante(estudante);
            Messages.addInfo(null, "Estudante salvo com sucesso");

        } catch (EstudanteBusinessException | LoginException e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void excluirEstudante(){
        Messages.addWarn(null, "Estudante excluído com sucesso");
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
