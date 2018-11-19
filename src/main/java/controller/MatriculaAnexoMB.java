package controller;

import model.Anexo;
import model.Matricula;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import service.AnexoService;
import service.MatriculaService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@ViewScoped
@Named("matriculaAnexoMB")
public class MatriculaAnexoMB extends ArquivoAbstract implements Serializable {

    private static final long serialVersionUID = 8963237834826726202L;
    private static final String DIRETORIO_MODULO = "matricula\\";

    @Inject
    private MatriculaService matriculaService;

    @Inject
    private AnexoService anexoService;

    private Matricula matricula;

    @Override
    protected void salvarAnexos(String caminho, String arquivo) {
        Anexo anexo = new Anexo();
        anexo.setCaminho(caminho);
        anexo.setNome(arquivo);
        anexo.setTipo("Comprovante Matrícula");
        matricula.getAnexos().add(anexo);
        anexoService.salvarAnexo(anexo);
    }

    
    protected List<Anexo> anexosAdicioandos(){
    	return  matricula.getAnexos();
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
    }
}
