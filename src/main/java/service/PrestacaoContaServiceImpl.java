package service;

import business.PrestacaoContaBusiness;
import dto.PrestacaoContaDTO;
import model.Estudante;
import model.PrestacaoConta;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class PrestacaoContaServiceImpl implements PrestacaoContaService, Serializable {

    private static final long serialVersionUID = -8498620217593646159L;

    @Inject
    PrestacaoContaBusiness prestacaoContaBusiness;

    @Override
    public void salvarPrestacao(PrestacaoConta prestacaoConta) {
        prestacaoContaBusiness.salvarPrestacao(prestacaoConta);
    }


    @Override
    public PrestacaoConta obterPrestacao(Long id_prestacaoConta) {
        return null;
    }

    @Override
    public List<Estudante> consultarEstudantes(PrestacaoContaDTO prestacaoContaDTO) {
        return null;
    }
}
