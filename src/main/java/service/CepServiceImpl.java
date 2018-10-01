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

    @Override
    public Endereco converterEndereco(EnderecoDTO enderecoDTO) {
        return null;
        // return cepBussines.converterEndereco(enderecoDTO);
    }

    @Override
    public EnderecoDTO converterEndereco(Endereco endereco) {
        return null;
        // return cepBussines.converterEndereco(endereco);
    }


}
