package business;

import dao.EdiatlDAO;
import model.Edital;
import service.EditalService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EditalBusiness {

    @Inject
    private EdiatlDAO ediatlDAO;


    private EditalService editalService;

    public void cadastrarEdital(Edital edital) {

        editalService.cadastrarEdital(edital);

        if(edital.getIdEdital() == null)
            ediatlDAO.save(edital);
        else
            ediatlDAO.update(edital);
    }
}
