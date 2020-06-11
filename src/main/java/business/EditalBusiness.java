package business;

import dao.EditalDAO;
import dto.EditalDTO;
import model.Edital;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EditalBusiness implements Serializable {

    private static final long serialVersionUID = -94532722529078881L;

    @Inject
    private EditalDAO editalDAO;

    public List<Edital> consultarEdital(EditalDTO editalDTO){
        return editalDAO.consultarEdital(editalDTO);
    }

    public void salvarEdital(Edital edital) {
        editalDAO.save(edital);
    }
    
    public Edital listarEdital(Long idEdital) {
    	return editalDAO.listarEdital(idEdital);
    }

    public void finalizarPeriodo(Edital edital) {
        edital.setFinalizado(Boolean.TRUE);
        salvarEdital(edital);
    }


    public List<Edital> listarEditais() {
        return editalDAO.list();
    }
}
