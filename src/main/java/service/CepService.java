package service;

import dto.EnderecoDTO;
import exception.CepBussinesException;
import model.Endereco;

import java.io.Serializable;

public interface CepService extends Serializable {

    Endereco getEnderecoCompleto(String cep) throws CepBussinesException;

}
