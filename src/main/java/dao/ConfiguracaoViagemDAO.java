package dao;

import generics.GenericDAO;
import model.ConfiguracaoViagem;

import java.util.List;

public class ConfiguracaoViagemDAO extends GenericDAO<ConfiguracaoViagem> {

    public List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital) {
        String sql = "SELECT cv FROM ConfiguracaoViagem cv " +
                "WHERE cv.edital.idEdital = :idEdital";

        return em.createQuery(sql)
                .setParameter("idEdital", idEdital)
                .getResultList();
    }

}
