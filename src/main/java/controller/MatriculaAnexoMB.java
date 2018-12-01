package controller;

import enumered.TipoAnexoEnum;
import model.Anexo;
import model.Matricula;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.AnexoService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    protected void salvarAnexos(String caminho, String arquivo) {
        Anexo anexo = new Anexo();
        anexo.setCaminho(caminho);
        anexo.setNome(arquivo);
        anexo.setTipo(TipoAnexoEnum.COMPROVANTE_MATRICULA.getDescricao());
        matricula.getAnexos().add(anexo);
        anexoService.salvarAnexo(anexo);
    }

    public StreamedContent download(Anexo anexo){
        String path = anexo.getCaminho() + anexo.getNome();
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
        return new DefaultStreamedContent(stream, "pdf", anexo.getNome());
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
