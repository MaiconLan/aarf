package service;

import business.ApplicationBusiness;

import javax.inject.Inject;

public class ApplicationServiceImpl implements ApplicationService {

    private static final long serialVersionUID = 1L;

    @Inject
    private ApplicationBusiness applicationBusiness;

    public String getNumeroVersao() {
        return applicationBusiness.getNumeroVersao();
    }

    public String getDescricaoVersao() {
        return applicationBusiness.getDescricaoVersao();
    }

    public String getVersaoCompleta() {
        return applicationBusiness.getVersaoCompleta();
    }

}