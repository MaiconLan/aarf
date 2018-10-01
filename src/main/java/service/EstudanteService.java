package service;

import exception.EstudanteBusinessException;
import model.Estudante;

import java.io.Serializable;
import java.util.List;

public interface EstudanteService extends Serializable {

    void salvarEstudante(Estudante estudante) throws EstudanteBusinessException;

    Estudante obterEstudante(Long idEstudante);

    List<Estudante> consultarEstudantesPorNome(String nome);

}
