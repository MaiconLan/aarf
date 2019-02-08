package dao;

import generics.GenericDAO;
import model.Atualizacao;

public class AtualizacaoDAO extends GenericDAO<Atualizacao> {

    public boolean possuiAtualizacaoPendente(){
        String sql = getSqlPossuiAtualizacaoPendente();
        return (boolean) em.createNativeQuery(sql).getSingleResult();
    }

    public Atualizacao obterAtualizacaoPendente() {
        String sql = getSqlAtualizacaoPendente();
        return (Atualizacao) em.createQuery(sql).setMaxResults(1).getSingleResult();
    }

    private String getSqlPossuiAtualizacaoPendente(){
        return "SELECT EXISTS(SELECT 1 FROM cadastro.atualizacao a WHERE current_date BETWEEN a.inicio AND termino AND( a.concluido IS NULL OR NOT a.concluido))";
    }

    private String getSqlAtualizacaoPendente(){
        return "SELECT a FROM Atualizacao a WHERE current_date BETWEEN a.inicio AND termino AND (a.concluido IS NULL OR a.concluido != TRUE) ";
    }
}
