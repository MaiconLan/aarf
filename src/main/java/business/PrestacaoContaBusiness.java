package business;

import dao.PrestacaoContaDAO;
import model.Estudante;
import model.PrestacaoConta;

import javax.inject.Inject;
import java.io.Serializable;

public class PrestacaoContaBusiness implements Serializable {

    private static final long serialVersionUID = -1524175203137307386L;

    @Inject
    private PrestacaoContaDAO prestacaoContaDAO;

    public void salvarPrestacao(PrestacaoConta prestacaoConta) {
        prestacaoContaDAO.save(prestacaoConta);
    }

    public PrestacaoConta obterPrestacao(Long idPrestacao){
        return prestacaoContaDAO.findById(idPrestacao);
    }

}
