package dao;

import dto.AssociadoDTO;
import generics.GenericDAO;
import model.Associado;
import utils.StringUtils;

import javax.persistence.Query;
import java.util.List;

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


    public boolean isCpfCadastrado(Associado associado){
        String cpf = StringUtils.removerCaracteres(associado.getPessoa().getCpf());
        Long idAssociado = associado.getIdAssociado();

        String sql = "SELECT EXISTS ( ";
        sql += "SELECT 1 FROM cadastro.associado a ";
        sql += "JOIN cadastro.pessoa p ON a.id_pessoa = p.id_pessoa ";
        sql += "WHERE p.cpf = :cpf ";

        if(idAssociado != null)
            sql += "AND a.id_associado <> :idAssociado ";

        sql += "AND (a.inativo IS NULL ";
        sql += "OR a.inativo <> 't')) ";

        Query query = em.createNativeQuery(sql);
        query.setParameter("cpf", cpf);

        if(idAssociado != null)
            query.setParameter("idAssociado", idAssociado);

        return (boolean) query.getSingleResult();
    }

    public boolean isRgCadastrado(Associado associado) {
        String rg = StringUtils.removerCaracteres(associado.getPessoa().getRg());
        Long idAssociado = associado.getIdAssociado();

        String sql = "SELECT EXISTS ( ";
        sql += "SELECT 1 FROM cadastro.associado a ";
        sql += "JOIN cadastro.pessoa p ON a.id_pessoa = p.id_pessoa ";
        sql += "WHERE p.rg = :rg ";

        if(idAssociado != null)
            sql += "AND a.id_associado <> :idAssociado ";

        sql += "AND (a.inativo IS NULL ";
        sql += "OR a.inativo <> 't')) ";

        Query query = em.createNativeQuery(sql);
        query.setParameter("rg", rg);

        if(idAssociado != null)
            query.setParameter("idAssociado", idAssociado);

        return (boolean) query.getSingleResult();
    }
}
