package controller;

import business.AnexoBusiness;
import enumered.TipoAnexoEnum;
import model.Anexo;
import model.Matricula;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("matriculaAnexoMB")
public class MatriculaAnexoMB extends ArquivoAbstract implements Serializable {

    private static final long serialVersionUID = 8963237834826726202L;
    private static final String DIRETORIO_MODULO = "/matricula";

    @Inject
    private AnexoBusiness anexoBusiness;

    private Matricula matricula;

    protected void salvarAnexos(Anexo anexo) {
        anexo.setTipo(TipoAnexoEnum.COMPROVANTE_MATRICULA.getDescricao());
        arquivosAdicionados.add(anexo);
        anexoBusiness.salvarAnexo(anexo);
    }

    @Override
    protected void removerAnexo(Anexo anexo) {
        matricula.getAnexos().remove(anexo);
        anexoBusiness.remover(anexo);
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
