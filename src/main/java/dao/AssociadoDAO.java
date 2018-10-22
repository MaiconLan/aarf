package dao;

import java.util.List;

import javax.persistence.Query;

import dto.AssociadoDTO;
import generics.GenericDAO;
import model.Associado;

public class AssociadoDAO extends GenericDAO<Associado> {

	public List<Associado> consultarAssociados(AssociadoDTO associadoDTO) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT a FROM Associado a ");
        sql.append("JOIN FETCH a.pessoa pessoa ");
        sql.append("JOIN FETCH pessoa.endereco ");
        sql.append("JOIN FETCH a.usuario usuario ");
        sql.append("WHERE (a.inativo = FALSE OR a.inativo IS NULL) ");
       

        if(associadoDTO.getIdAssociado() != null)
            sql.append("AND a.idAssociado = :idAssociado ");

        if(associadoDTO.getNome() != null && !associadoDTO.getNome().isEmpty())
            sql.append("AND LOWER(pessoa.nome) LIKE '%' || LOWER(:nome) || '%' ");
        
        if(associadoDTO.getCargo() != null && !associadoDTO.getCargo().isEmpty())
        	sql.append("AND a.cargo = :cargo ");

        if(associadoDTO.getBairro() != null && !associadoDTO.getBairro().isEmpty())
            sql.append("AND endereco.bairro = :bairro ");

        if(associadoDTO.getCpf() != null && !associadoDTO.getCpf().isEmpty())
            sql.append("AND pessoa.cpf = :cpf ");

        if(associadoDTO.getRg() != null && !associadoDTO.getRg().isEmpty())
            sql.append("AND pessoa.rg = :rg ");

        if(associadoDTO.getCelular() != null && !associadoDTO.getCelular().isEmpty())
            sql.append("AND pessoa.celular = :celular ");

        if(associadoDTO.getTelefone() != null && !associadoDTO.getTelefone().isEmpty())
            sql.append("AND pessoa.telefone = :telefone ");

        Query query = em.createQuery(sql.toString());

        if(associadoDTO.getIdAssociado() != null)
           query.setParameter("idAssociado", associadoDTO.getIdAssociado());

        if(associadoDTO.getNome() != null && !associadoDTO.getNome().isEmpty())
            query.setParameter("nome", associadoDTO.getNome());

        if(associadoDTO.getBairro() != null && !associadoDTO.getBairro().isEmpty())
            query.setParameter("bairro", associadoDTO.getBairro());

        if(associadoDTO.getCelular() != null && !associadoDTO.getCelular().isEmpty())
            query.setParameter("celular", associadoDTO.getCelular());

        if(associadoDTO.getTelefone() != null && !associadoDTO.getTelefone().isEmpty())
            query.setParameter("telefone", associadoDTO.getTelefone());

        if(associadoDTO.getCpf() != null && !associadoDTO.getCpf().isEmpty())
            query.setParameter("cpf", associadoDTO.getCpf());

        if(associadoDTO.getRg() != null && !associadoDTO.getRg().isEmpty())
            query.setParameter("rg", associadoDTO.getRg());
        
        if(associadoDTO.getCargo() != null && !associadoDTO.getCargo().isEmpty())
            query.setParameter("cargo", associadoDTO.getCargo());

        return query.getResultList();
    }

}
