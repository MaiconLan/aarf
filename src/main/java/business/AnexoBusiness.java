package business;

import dao.AnexoDAO;
import model.Anexo;

import javax.inject.Inject;

public class AnexoBusiness {

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
