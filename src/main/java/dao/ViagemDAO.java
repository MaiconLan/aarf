package dao;

import dto.FiltroViagemDTO;
import generics.GenericDAO;
import model.Viagem;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ViagemDAO extends GenericDAO<Viagem> {

    public List<Viagem> buscarViagens(FiltroViagemDTO filtro) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT v FROM Viagem v ");
        sql.append("JOIN v.matricula m ");
        sql.append("LEFT JOIN m.cancelamento c ");
        sql.append("WHERE c.idCancelamento IS NULL ");

        if(filtro.getDiaSemana() != null && filtro.getDiaSemana().length > 0)
            sql.append("AND v.diaSemana IN (:diaSemana) ");

        if(filtro.getSentido() != null && filtro.getSentido().length > 0)
            sql.append("AND v.sentido IN (:sentido) ");

        if(filtro.getInstituicao() != null)
            sql.append("AND v.instituicao.idInstituicao = :idInstituicao ");

        if(filtro.getIdEdital() != null)
            sql.append("AND m.edital.idEdital = :idEdital ");

        Query query = em.createQuery(sql.toString());

        if(filtro.getDiaSemana() != null && filtro.getDiaSemana().length > 0)
            query.setParameter("diaSemana", Arrays.asList(filtro.getDiaSemana()));

        if(filtro.getSentido() != null && filtro.getSentido().length > 0)
            query.setParameter("sentido", Arrays.asList(filtro.getSentido()));

        if(filtro.getInstituicao() != null)
            query.setParameter("idInstituicao", filtro.getInstituicao().getIdInstituicao());

        if(filtro.getIdEdital() != null)
            query.setParameter("idEdital", filtro.getIdEdital());

        return query.getResultList();
    }
}
