package dao;

import dto.FiltroViagemDTO;
import dto.ViagemDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Viagem;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ViagemDAO extends GenericDAOV2<Viagem, Long> {

    public List<Viagem> buscarViagens(FiltroViagemDTO filtro) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT v FROM Viagem v ");
        sql.append("JOIN v.matricula m ");
        sql.append("LEFT JOIN m.cancelamento c ");
        sql.append("WHERE c.idCancelamento IS NULL ");

        if (filtro.getDiaSemana() != null && filtro.getDiaSemana().length > 0)
            sql.append("AND v.diaSemana IN (:diaSemana) ");

        if (filtro.getSentido() != null && filtro.getSentido().length > 0)
            sql.append("AND v.sentido IN (:sentido) ");

        if (filtro.getInstituicao() != null)
            sql.append("AND v.instituicao.idInstituicao = :idInstituicao ");

        if (filtro.getIdEdital() != null)
            sql.append("AND m.edital.idEdital = :idEdital ");

        Query query = getEntityManager().createQuery(sql.toString());

        if (filtro.getDiaSemana() != null && filtro.getDiaSemana().length > 0)
            query.setParameter("diaSemana", Arrays.asList(filtro.getDiaSemana()));

        if (filtro.getSentido() != null && filtro.getSentido().length > 0)
            query.setParameter("sentido", Arrays.asList(filtro.getSentido()));

        if (filtro.getInstituicao() != null)
            query.setParameter("idInstituicao", filtro.getInstituicao().getIdInstituicao());

        if (filtro.getIdEdital() != null)
            query.setParameter("idEdital", filtro.getIdEdital());

        return query.getResultList();
    }

    public List<ViagemDTO> buscarViagensDTO(Long idEdital, Long idInstituicao) {
        String sql = "SELECT new dto.ViagemDTO(v.matricula.idMatricula, v.instituicao.idInstituicao, v.matricula.estudante.pessoa.nome, SUM(v.valor), COUNT(v.idViagem)) "
                + "FROM Viagem v "
                + "JOIN v.matricula m "
                + "LEFT JOIN m.cancelamento c "
                + "WHERE c.idCancelamento IS NULL "
                + "AND v.matricula.edital.idEdital = :idEdital "
                + "AND v.instituicao.idInstituicao = :idInstituicao" +
                " GROUP BY v.matricula.idMatricula, v.instituicao.idInstituicao, v.matricula.estudante.pessoa.nome";

        return getEntityManager().createQuery(sql)
                .setParameter("idEdital", idEdital)
                .setParameter("idInstituicao", idInstituicao)
                .getResultList();
    }

    public List<Viagem> buscarViagens(Long idEdital, Long idInstituicao) {
        String sql = "SELECT v "
                + "FROM Viagem v "
                + "JOIN v.matricula m "
                + "LEFT JOIN m.cancelamento c "
                + "WHERE c.idCancelamento IS NULL "
                + "AND v.matricula.edital.idEdital = :idEdital "
                + "AND v.instituicao.idInstituicao = :idInstituicao";

        return getEntityManager().createQuery(sql)
                .setParameter("idEdital", idEdital)
                .setParameter("idInstituicao", idInstituicao)
                .getResultList();
    }

    public List<Viagem> buscarViagensByMatriculaInstituicao(Long idMatricula, Long idInstituicao) {
        String sql = "SELECT v "
                + "FROM Viagem v "
                + "JOIN v.matricula m "
                + "LEFT JOIN m.cancelamento c "
                + "WHERE c.idCancelamento IS NULL "
                + "AND v.matricula.idMatricula = :idMatricula "
                + "AND v.instituicao.idInstituicao = :idInstituicao";

        return getEntityManager().createQuery(sql)
                .setParameter("idMatricula", idMatricula)
                .setParameter("idInstituicao", idInstituicao)
                .getResultList();
    }
}
