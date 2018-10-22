package dao;

import dto.EstudanteDTO;
import generics.GenericDAO;
import model.Estudante;

import javax.persistence.Query;
import java.util.List;

public class EstudanteDAO extends GenericDAO<Estudante> {


    public List<Estudante> consultarEstudantes(EstudanteDTO estudanteDTO) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT e FROM Estudante e ");
        sql.append("JOIN FETCH e.pessoa pessoa ");
        sql.append("JOIN FETCH pessoa.endereco ");
        sql.append("JOIN FETCH e.usuario usuario ");
        sql.append("WHERE (e.inativo = FALSE OR e.inativo IS NULL) ");

        if(estudanteDTO.getIdEstudante() != null)
            sql.append("AND e.idEstudante = :idEstudante ");

        if(estudanteDTO.getNome() != null && !estudanteDTO.getNome().isEmpty())
            sql.append("AND LOWER(pessoa.nome) LIKE '%' || LOWER(:nome) || '%' ");

        if(estudanteDTO.getBairro() != null && !estudanteDTO.getBairro().isEmpty())
            sql.append("AND endereco.bairro = :bairro ");

        if(estudanteDTO.getCpf() != null && !estudanteDTO.getCpf().isEmpty())
            sql.append("AND pessoa.cpf = :cpf ");

        if(estudanteDTO.getRg() != null && !estudanteDTO.getRg().isEmpty())
            sql.append("AND pessoa.rg = :rg ");

        if(estudanteDTO.getCelular() != null && !estudanteDTO.getCelular().isEmpty())
            sql.append("AND pessoa.celular = :celular ");

        if(estudanteDTO.getTelefone() != null && !estudanteDTO.getTelefone().isEmpty())
            sql.append("AND pessoa.telefone = :telefone ");

        Query query = em.createQuery(sql.toString());

        if(estudanteDTO.getIdEstudante() != null)
           query.setParameter("idEstudante", estudanteDTO.getIdEstudante());

        if(estudanteDTO.getNome() != null && !estudanteDTO.getNome().isEmpty())
            query.setParameter("nome", estudanteDTO.getNome());

        if(estudanteDTO.getBairro() != null && !estudanteDTO.getBairro().isEmpty())
            query.setParameter("bairro", estudanteDTO.getBairro());

        if(estudanteDTO.getCelular() != null && !estudanteDTO.getCelular().isEmpty())
            query.setParameter("celular", estudanteDTO.getCelular());

        if(estudanteDTO.getTelefone() != null && !estudanteDTO.getTelefone().isEmpty())
            query.setParameter("telefone", estudanteDTO.getTelefone());

        if(estudanteDTO.getCpf() != null && !estudanteDTO.getCpf().isEmpty())
            query.setParameter("cpf", estudanteDTO.getCpf());

        if(estudanteDTO.getRg() != null && !estudanteDTO.getRg().isEmpty())
            query.setParameter("rg", estudanteDTO.getRg());

        return query.getResultList();
    }
}
