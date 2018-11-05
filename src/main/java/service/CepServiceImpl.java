package service;

import business.CepBussines;
import dto.EnderecoDTO;
import exception.CepBussinesException;
import model.Endereco;
import service.CepService;

import javax.inject.Inject;

public class CepServiceImpl implements CepService {

    @Inject
    private CepBussines cepBussines;

    @Override
    public Endereco getEnderecoCompleto(String cep) throws CepBussinesException {
        return cepBussines.getEnderecoCompleto(cep);
    }

}
