package dao;

import java.util.List;

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
        Query query = em.createQuery(sql.toString());

        return query.getResultList();
    }

    public Matricula obterMatricula(Long idEstudante) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT m FROM Matricula m ");
        sql.append("JOIN m.estudante e ");

        sql.append("WHERE e.idEstudante = :idEstudante ");
        Query query = em.createQuery(sql.toString());

        return (Matricula) query.setMaxResults(1)
                .setParameter("idEstudante", idEstudante)
                .getSingleResult();
    }
}
