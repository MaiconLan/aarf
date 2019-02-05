package service;

import java.util.List;

import model.Anexo;

public interface AnexoService {

    void salvarAnexo(Anexo anexo);

    void remover(Anexo anexo);

    void removerMatriculaAnexo(Anexo anexo, Long idMatricula);
}
