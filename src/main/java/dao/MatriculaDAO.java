package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import dto.MatriculaDTO;
import enumered.MatriculaSituacao;
import generics.GenericDAO;
import model.Matricula;

public class MatriculaDAO extends GenericDAO<Matricula> {

 public List<Matricula> buscarMatriculas(Matricula filtro) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT m FROM Matricula m ");
        sql.append("JOIN m.estudante e ");
        sql.append("JOIN m.edital ed ");
    
        sql.append("WHERE m.cancelamento.idCancelamento IS NULL ");
        Query query = em.createQuery(sql.toString());

        return query.getResultList();
    }

    public Matricula obterMatriculaByIdEstudante(Long idEstudante) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT m FROM Matricula m ");
        sql.append("JOIN FETCH m.estudante e ");
        sql.append("JOIN m.edital edital ");
        sql.append("LEFT JOIN m.cancelamento c ");

        sql.append("WHERE e.idEstudante = :idEstudante ");
        sql.append("AND c.idCancelamento IS NULL ");
        sql.append("AND (edital.finalizado IS NULL OR edital.finalizado = FALSE) ");
        Query query = em.createQuery(sql.toString());

        try {
            return (Matricula) query.setMaxResults(1)
                    .setParameter("idEstudante", idEstudante)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Matricula> listarMatriculasEmAprovacao(MatriculaDTO matriculaDTO) {
        StringBuilder sql = getSqlObterMatricula();
        sql.append("WHERE matricula.situacao = :situacao ");
        sql.append("AND cancelamento.idCancelamento IS NULL ");

        if(matriculaDTO.getIdInstituicao() != null)
            sql.append("AND instituicao.idInstituicao = :idInstituicao ");

        Query query = em.createQuery(sql.toString());

        query.setParameter("situacao", MatriculaSituacao.EM_APROVACAO.getDescricao());

        if(matriculaDTO.getIdInstituicao() != null)
            query.setParameter("idInstituicao", matriculaDTO.getIdInstituicao());


        return query.getResultList();
    }

    public List<Matricula> listarMatriculasByIdEstudante(Long idEstudante) {
        StringBuilder sql = getSqlObterMatricula();
        sql.append("WHERE estudante.idEstudante = :idEstudante ");

        return em.createQuery(sql.toString())
                .setParameter("idEstudante", idEstudante)
                .getResultList();
    }

    private StringBuilder getSqlObterMatricula(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT matricula FROM Matricula matricula ");
        sql.append("JOIN matricula.estudante estudante ");
        sql.append("JOIN matricula.edital edital ");
        sql.append("JOIN estudante.instituicao instituicao ");
        sql.append("LEFT JOIN matricula.cancelamento cancelamento ");


        return sql;
    }
}
