package dao;

import dto.InstituicaoDTO;
import generics.GenericDAO;
import model.Instituicao;
import model.PrestacaoConta;

import javax.persistence.Query;
import java.util.List;

public class PrestacaoContaDAO extends GenericDAO<PrestacaoConta> {
    public List<PrestacaoConta> obterPrestacoes() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT p FROM PrestacaoConta p ");
        sql.append("WHERE 1=1 ");

        Query query = em.createQuery(sql.toString());

        return query.getResultList();
    }

}
