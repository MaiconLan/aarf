package controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("matriculaAnexoMB")
public class MatriculaAnexoMB extends ArquivoAbstract implements Serializable {

    private static final long serialVersionUID = 8963237834826726202L;
    private static final String DIRETORIO_MODULO = "matricula\\";

    @Override
    protected String obterDiretorioModulo() {
        return DIRETORIO_MODULO;
    }


}
