package dao;

import generics.GenericDAO;
import generics.GenericDAOV2;
import model.ConfiguracaoViagem;

import javax.persistence.NoResultException;
import java.util.List;

public class ConfiguracaoViagemDAO extends GenericDAOV2<ConfiguracaoViagem, Long> {

    public List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital) {
        String sql = "SELECT cv FROM ConfiguracaoViagem cv " +
                "WHERE cv.edital.idEdital = :idEdital";

        return getEntityManager().createQuery(sql)
                .setParameter("idEdital", idEdital)
                .getResultList();
    }

    public ConfiguracaoViagem obterConfiguracaoViagem(Long idEdital, Long idInstituicao) {
        String sql = "SELECT cv FROM ConfiguracaoViagem cv " +
                "WHERE cv.edital.idEdital = :idEdital " +
                "AND cv.instituicao.idInstituicao = :idInstituicao ";

        try {
            return getEntityManager().createQuery(sql, ConfiguracaoViagem.class)
                    .setParameter("idEdital", idEdital)
                    .setParameter("idInstituicao", idInstituicao)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Boolean isEditalFinalizadoByMatricula(Long idMatricula) {
        String query = "SELECT CAST(e.finalizado AS boolean)" +
                "FROM Edital e " +
                "JOIN e.matriculas m " +
                "WHERE m.idMatricula = :idMatricula";

        return (Boolean) getEntityManager().createQuery(query)
                .setParameter("idMatricula", idMatricula)
                .setMaxResults(1)
                .getSingleResult();
    }

    public boolean isMatriculaCancelada(Long idMatricula) {
        String query = "SELECT COUNT (c.idCancelamento) > 0 " +
                "FROM Cancelamento c " +
                "WHERE c.matricula.idMatricula = :idMatricula";
        try {
            return (boolean) getEntityManager().createQuery(query)
                    .setParameter("idMatricula", idMatricula)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
    }
}
