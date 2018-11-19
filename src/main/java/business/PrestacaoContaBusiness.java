package business;

import dao.PrestacaoContaDAO;
import model.Estudante;
import model.PrestacaoConta;

import javax.inject.Inject;

public class PrestacaoContaBusiness {

    @Inject
    private PrestacaoContaDAO prestacaoContaDAO;

    public void salvarPrestacao(PrestacaoConta prestacaoConta) {
        if (prestacaoConta.getId_prestacaoConta() == null) {
        prestacaoContaDAO.save(prestacaoConta);
        } else {
            prestacaoContaDAO.update(prestacaoConta);
        }
    }

    public PrestacaoConta obterPrestacao(Long idPrestacao){
        return prestacaoContaDAO.findById(idPrestacao);
    }




}
