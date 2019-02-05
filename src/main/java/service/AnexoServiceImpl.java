package service;

import business.AnexoBusiness;
import dao.AnexoDAO;
import model.Anexo;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class AnexoServiceImpl implements AnexoService, Serializable {

    private static final long serialVersionUID = 3506639285039218471L;

    @Inject
    private AnexoDAO anexoDAO;

    @Inject
    private AnexoBusiness anexoBusiness;

    @Override
    public void salvarAnexo(Anexo anexo) {
        anexoDAO.save(anexo);
    }

    @Override
    public void remover(Anexo anexo) {
        anexoDAO.remove(anexo.getIdAnexo());
    }

    @Override
    public void removerMatriculaAnexo(Anexo anexo, Long idMatriculao) {
        anexoBusiness.removerMatriculaAnexo(anexo, idMatriculao);
    }
}
