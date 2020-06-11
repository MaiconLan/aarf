package dao;


import dto.BancoDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Banco;

import javax.persistence.Query;
import java.util.List;


public class BancoDAO extends GenericDAOV2<Banco, Long> {

    public List<Banco> consultaBanco(BancoDTO bancoDTO) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b FROM Banco b ");
        sql.append("WHERE 1=1 ");

        if(bancoDTO.getIdBanco() != null)
            sql.append("AND b.idBanco = :idbanco ");

        if(bancoDTO.getNome() != null && !bancoDTO.getNome().isEmpty())
            sql.append("AND b.nome = :nome ");

        Query query = getEntityManager().createQuery(sql.toString());

        if(bancoDTO.getIdBanco() != null)
            query.setParameter("idBanco", bancoDTO.getIdBanco());

        if(bancoDTO.getNome() != null && !bancoDTO.getNome().isEmpty())
            query.setParameter("nome", bancoDTO.getNome());

        return query.getResultList();
    }


}
