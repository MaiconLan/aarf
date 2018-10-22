package service;

import java.io.Serializable;

public interface ApplicationService extends Serializable {

    public String getNumeroVersao();

    public String getDescricaoVersao();

    public String getVersaoCompleta();

}