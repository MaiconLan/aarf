package service;

import java.io.Serializable;
import model.Edital;

public interface EditalService extends Serializable {

    void cadastrarEdital(Edital edital);

}
