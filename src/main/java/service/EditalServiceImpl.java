package service;

import business.EditalBusiness;
import dto.EditalDTO;
import model.Edital;
import org.primefaces.util.LimitedSizeHashMap;

import javax.inject.Inject;
import java.util.List;

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

    @Override
    public List<Edital> consultarEdital(EditalDTO editalDTO) {
        return editalBusiness.consultarEdital(editalDTO);
    }

}
