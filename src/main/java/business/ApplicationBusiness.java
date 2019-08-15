package business;

import utils.Versao;

import java.io.Serializable;

public class ApplicationBusiness implements Serializable {

    private static final long serialVersionUID = -3008501536558282343L;

    public String getVersaoCompleta() {
        return "Versão: " + Versao.MAJOR.getNumero() +
                "." + Versao.MINOR.getNumero() +
                "." + Versao.RELEASE.getNumero();
    }

}