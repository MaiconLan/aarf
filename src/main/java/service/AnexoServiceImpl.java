package service;

import dao.AnexoDAO;
import model.Anexo;

import javax.inject.Inject;
import java.io.Serializable;

public class AnexoServiceImpl implements AnexoService, Serializable {

    private static final long serialVersionUID = 3506639285039218471L;


    @Inject
    private AnexoDAO anexoDAO;

    @Override
    public void salvarAnexo(Anexo anexo) {
        anexoDAO.save(anexo);
    }
}
