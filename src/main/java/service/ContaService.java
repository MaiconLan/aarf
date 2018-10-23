package service;

import exception.ContaBusinessException;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Conta;

import java.io.Serializable;
import java.util.List;

public interface ContaService extends Serializable {

    void salvarConta(Conta conta) throws ContaBusinessException;
    Conta obterConta(Long idConta);

}
