package dao;

import dto.InstituicaoDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Instituicao;
import model.PrestacaoConta;

import javax.persistence.Query;
import java.util.List;

public class PrestacaoContaDAO extends GenericDAOV2<PrestacaoConta, Long> {
    public List<PrestacaoConta> obterPrestacoes() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT p FROM PrestacaoConta p ");
        sql.append("WHERE 1=1 ");

        Query query = getEntityManager().createQuery(sql.toString());

        return query.getResultList();
    }

}
