package service;

import dto.ContaDTO;
import exception.ContaBusinessException;
import model.Conta;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface ContaService extends Serializable {

    void salvarConta(Conta conta) throws ContaBusinessException;

    Conta obterConta(Long idConta);

    void inativarConta(Conta conta) throws ContaBusinessException;

    List<Conta> consultarConta(ContaDTO contaDTO);
}
