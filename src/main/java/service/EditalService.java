package service;

import java.io.Serializable;
import model.Edital;

public interface EditalService extends Serializable {

    void salvarEdital(Edital edital);

    void finalizarPeriodo(Edital edital);
}
