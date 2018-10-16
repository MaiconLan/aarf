package service;

import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Estudante;

import java.io.Serializable;
import java.util.List;

public interface AssociadoService extends Serializable {

    void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException;

    Estudante obterEstudante(Long idAssociado);

    List<Estudante> consultarAssociadoPorNome(String nome);

}
