package service;

import business.EditalBusiness;
import model.Edital;

import javax.inject.Inject;

public class EditalServiceImpl implements EditalService {

    private static final long serialVersionUID = -7716606788554145867L;

    @Inject
    private EditalBusiness editalBusiness;

    @Override
    public void salvarEdital(Edital edital) {
        editalBusiness.salvarEdital(edital);
    }

    @Override
    public void finalizarPeriodo(Edital edital) {
        editalBusiness.finalizarPeriodo(edital);
    }

	@Override
	public Edital listarEdital(Long idEdital) {
		return editalBusiness.listarEdital(idEdital);
	}
}
