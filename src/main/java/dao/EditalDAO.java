package dao;

import generics.GenericDAO;
import model.Edital;

public class EditalDAO extends GenericDAO<Edital> {
	
	public Edital listarEdital(Long idEdital) {
		return em.find(Edital.class, idEdital);
	}
}
