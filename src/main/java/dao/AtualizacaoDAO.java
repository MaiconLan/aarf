package dao;

import generics.GenericDAOV2;
import model.Atualizacao;

public class AtualizacaoDAO extends GenericDAOV2<Atualizacao, Long> {

    public boolean possuiAtualizacaoPendente(){
        String sql = getSqlPossuiAtualizacaoPendente();
        return (boolean) getEntityManager().createNativeQuery(sql).getSingleResult();
    }

    public Atualizacao obterAtualizacaoPendente() {
        String sql = getSqlAtualizacaoPendente();
        return (Atualizacao) getEntityManager().createQuery(sql).setMaxResults(1).getSingleResult();
    }

    private String getSqlPossuiAtualizacaoPendente(){
        return "SELECT EXISTS(SELECT 1 FROM cadastro.atualizacao a WHERE current_date BETWEEN a.inicio AND termino AND( a.concluido IS NULL OR NOT a.concluido))";
    }

    private String getSqlAtualizacaoPendente(){
        return "SELECT a FROM Atualizacao a WHERE current_date BETWEEN a.inicio AND termino AND (a.concluido IS NULL OR a.concluido != TRUE) ";
    }
}
