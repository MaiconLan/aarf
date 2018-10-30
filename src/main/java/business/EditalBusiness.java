package business;

import dao.EditalDAO;
import model.Edital;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EditalBusiness {

    @Inject
    private EditalDAO editalDAO;

    public void salvarEdital(Edital edital) {
        if(edital.getIdEdital() == null)
            editalDAO.save(edital);
        else
            editalDAO.update(edital);
    }
    
    public Edital listarEdital(Long idEdital) {
    	return editalDAO.listarEdital(idEdital);
    }

    public void finalizarPeriodo(Edital edital) {
        edital.setFinalizado(Boolean.TRUE);
        salvarEdital(edital);
    }
}
