package service;

import business.PrestacaoContaBusiness;
import dao.PrestacaoContaDAO;
import dto.PrestacaoContaDTO;
import model.Estudante;
import model.PrestacaoConta;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class PrestacaoContaServiceImpl implements PrestacaoContaService, Serializable {

    private static final long serialVersionUID = -8498620217593646159L;

    @Inject
    private PrestacaoContaDAO prestacaoContaDAO = new PrestacaoContaDAO();
    @Inject
    PrestacaoContaBusiness prestacaoContaBusiness;

    @Override
    public void salvarPrestacao(PrestacaoConta prestacaoConta) {
        prestacaoContaBusiness.salvarPrestacao(prestacaoConta);
    }


    @Override
    public List<PrestacaoConta> obterPrestacao() {
       return prestacaoContaDAO.obterPrestacoes();
    }

    @Override
    public List<Estudante> consultarEstudantes(PrestacaoContaDTO prestacaoContaDTO) {
        return null;
    }
}
