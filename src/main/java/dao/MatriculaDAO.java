package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import generics.GenericDAO;
import model.Matricula;

public class MatriculaDAO extends GenericDAO<Matricula> {

 public List<Matricula> buscarMatriculas(Matricula filtro) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT m FROM Matricula m ");
        sql.append("JOIN m.estudante e ");
        sql.append("JOIN m.edital ed ");
    
        sql.append("WHERE m.confirmacao is null ");
        sql.append("AND m.cancelamento.idCancelamento IS NULL ");
        Query query = em.createQuery(sql.toString());

        return query.getResultList();
    }

    public Matricula obterMatricula(Long idEstudante) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT m FROM Matricula m ");
        sql.append("JOIN FETCH m.estudante e ");
        sql.append("LEFT JOIN m.cancelamento c ");

        sql.append("WHERE e.idEstudante = :idEstudante ");
        sql.append("AND c.idCancelamento IS NULL ");
        Query query = em.createQuery(sql.toString());

        try {
            return (Matricula) query.setMaxResults(1)
                    .setParameter("idEstudante", idEstudante)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
