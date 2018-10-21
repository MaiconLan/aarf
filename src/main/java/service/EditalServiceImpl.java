package service;

import business.EditalBusiness;
import model.Edital;

import javax.inject.Inject;

public class EditalServiceImpl implements EditalService {

    private static final long serialVersionUID = -7716606788554145867L;


    private EditalBusiness editalBusiness;

    @Override
    public void cadastrarEdital(Edital edital) {
        editalBusiness.cadastrarEdital(edital);
    }
}
