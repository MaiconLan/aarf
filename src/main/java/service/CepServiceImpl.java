package service;

import business.CepBussines;
import dto.EnderecoDTO;
import model.Endereco;
import service.CepService;

import javax.inject.Inject;

public class CepServiceImpl implements CepService {

    @Inject
    private CepBussines cepBussines;

    @Override
    public Endereco getEnderecoCompleto(String cep) {
        return cepBussines.getEnderecoCompleto(cep);
    }

}
