package service;

import java.io.Serializable;
import java.util.List;

import dto.EditalDTO;
import model.Edital;

public interface EditalService extends Serializable {

    void salvarEdital(Edital edital);

    void finalizarPeriodo(Edital edital);

    List<Edital> consultarEdital(EditalDTO editalDTO);
}
