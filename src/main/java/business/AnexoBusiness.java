package business;

import dao.AnexoDAO;
import model.Anexo;

import javax.inject.Inject;
import java.io.Serializable;

public class AnexoBusiness implements Serializable {

    private static final long serialVersionUID = -8262433147845488476L;

    @Inject
    private AnexoDAO anexoDAO;

    public void removerMatriculaAnexo(Anexo anexo, Long idMatricula){
        anexoDAO.removerMatriculaAnexo(anexo, idMatricula);
    }

    public void salvarAnexo(Anexo anexo) {
        anexoDAO.save(anexo);
    }

    public void remover(Anexo anexo) {
        anexoDAO.remove(anexo.getIdAnexo());
    }
}
