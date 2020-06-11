package dao;

import dto.EditalDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Edital;

import javax.persistence.Query;
import java.util.List;

public class EditalDAO extends GenericDAOV2<Edital, Long> {
	
	public Edital listarEdital(Long idEdital) {
		return getEntityManager().find(Edital.class, idEdital);
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

        Query query = getEntityManager().createQuery(sql.toString());

        if (editalDTO.getTitulo() != null && !editalDTO.getTitulo().isEmpty()) {
            query.setParameter("titulo", editalDTO.getTitulo());
        }

        return query.getResultList();
    }
}
