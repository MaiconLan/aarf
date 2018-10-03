package business;

import dao.ApplicationDAO;
import utils.Unit;

import javax.inject.Inject;
import java.io.Serializable;

public class ApplicationBusiness implements Serializable {

    private static final long serialVersionUID = -3008501536558282343L;

    @Inject
    private ApplicationDAO applicationDAO;

    public String getDescricaoVersao() {
        return Unit.DESCRICAO_VERSAO;
    }

    public String getNumeroVersao() {
        return Unit.NUMERO_VERSAO + "." + applicationDAO.getVersao();
    }

    public String getVersaoCompleta() {
        return getDescricaoVersao() + getNumeroVersao() + Unit.SNAPSHOT;
    }

}