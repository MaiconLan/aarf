package dao;

import dto.ContaDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Conta;

import javax.persistence.Query;
import java.util.List;

public class ContaDAO extends GenericDAOV2<Conta, Long> {

    public List<Conta> consultarConta(ContaDTO contaDTO) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT c FROM Conta c ");
        sql.append("WHERE (c.inativo = FALSE OR c.inativo IS NULL) ");

        if(contaDTO.getIdConta() != null)
            sql.append("AND c.idConta = :idConta ");

        if(contaDTO.getTitular() != null && !contaDTO.getTitular().isEmpty())
            sql.append("AND LOWER(c.titular) LIKE '%' || LOWER(:titular) || '%' ");

        if(contaDTO.getAgencia() != null)
            sql.append("AND c.agencia = :agencia ");

        if(contaDTO.getNumeroConta() != null)
            sql.append("AND c.numeroConta = :numeroConta ");

        Query query = getEntityManager().createQuery(sql.toString());

        if(contaDTO.getIdConta() != null)
            query.setParameter("idConta", contaDTO.getIdConta());

        if(contaDTO.getTitular() != null && !contaDTO.getTitular().isEmpty())
            query.setParameter("titular", contaDTO.getTitular());

        if(contaDTO.getAgencia() != null)
            query.setParameter("agencia", contaDTO.getAgencia());

        if(contaDTO.getNumeroConta() != null)
            query.setParameter("numeroConta", contaDTO.getNumeroConta());

        return query.getResultList();
    }
}
