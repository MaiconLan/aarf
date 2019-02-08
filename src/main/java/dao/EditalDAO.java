package dao;

import dto.EditalDTO;
import generics.GenericDAO;
import model.Edital;

import javax.persistence.Query;
import java.util.List;

public class EditalDAO extends GenericDAO<Edital> {
	
	public Edital listarEdital(Long idEdital) {
		return em.find(Edital.class, idEdital);
	}

    public List consultarEdital(EditalDTO editalDTO) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM Edital e ");
        sql.append("WHERE 1=1 ");

        if (editalDTO.getTitulo() != null && !editalDTO.getTitulo().isEmpty()) {
            sql.append("AND LOWER(e.titulo) LIKE '%' || LOWER(:titulo) || '%' ");
        }

        sql.append("ORDER BY e.inicio ");

        if(!editalDTO.isOrder())
            sql.append("DESC ");

        Query query = em.createQuery(sql.toString());

        if (editalDTO.getTitulo() != null && !editalDTO.getTitulo().isEmpty()) {
            query.setParameter("titulo", editalDTO.getTitulo());
        }

        return query.getResultList();
    }
}
