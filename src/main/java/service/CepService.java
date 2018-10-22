package service;

import dto.EnderecoDTO;
import model.Endereco;

import java.io.Serializable;

public interface CepService extends Serializable {

    Endereco getEnderecoCompleto(String cep);

}
