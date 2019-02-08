package controller;

import enumered.TipoAnexoEnum;
import model.Anexo;
import model.Matricula;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.AnexoService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("matriculaAnexoMB")
public class MatriculaAnexoMB extends ArquivoAbstract implements Serializable {

    private static final long serialVersionUID = 8963237834826726202L;
    private static final String DIRETORIO_MODULO = "\\matricula\\";

    @Inject
    private AnexoService anexoService;

    private Matricula matricula;

    protected void salvarAnexos(Anexo anexo) {
        anexo.setTipo(TipoAnexoEnum.COMPROVANTE_MATRICULA.getDescricao());
        arquivosAdicionados.add(anexo);
        anexoService.salvarAnexo(anexo);
    }

    @Override
    protected void removerAnexo(Anexo anexo) {
        matricula.getAnexos().remove(anexo);
        anexoService.remover(anexo);
    }

    protected List<Anexo> anexosAdicionados(){
        return arquivosAdicionados;
    }

    @Override
    protected String obterDiretorioModulo() {
        return DIRETORIO_MODULO;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
        arquivosAdicionados = matricula.getAnexos();
    }

    @Override
    public void remover(Anexo anexo){
        matricula.getAnexos().remove(anexo);
        super.remover(anexo);
    }
}
