package dao;

import generics.GenericDAO;
import model.ConfiguracaoViagem;

import javax.persistence.NoResultException;
import java.util.List;

public class ConfiguracaoViagemDAO extends GenericDAO<ConfiguracaoViagem> {

    public List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital) {
        String sql = "SELECT cv FROM ConfiguracaoViagem cv " +
                "WHERE cv.edital.idEdital = :idEdital";

        return em.createQuery(sql)
                .setParameter("idEdital", idEdital)
                .getResultList();
    }

    public ConfiguracaoViagem obterConfiguracaoViagem(Long idEdital, Long idInstituicao) {
        String sql = "SELECT cv FROM ConfiguracaoViagem cv " +
                "WHERE cv.edital.idEdital = :idEdital " +
                "AND cv.instituicao.idInstituicao = :idInstituicao ";

        try {
            return em.createQuery(sql, ConfiguracaoViagem.class)
                    .setParameter("idEdital", idEdital)
                    .setParameter("idInstituicao", idInstituicao)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
