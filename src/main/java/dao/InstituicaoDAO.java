package dao;

import java.util.List;

import javax.persistence.Query;

import dto.InstituicaoDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Instituicao;
import model.TipoInstituicao;

public class InstituicaoDAO extends GenericDAOV2<Instituicao, Long> {

	public List<Instituicao> consultarInstituicoes(InstituicaoDTO instituicaoDTO) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT i FROM Instituicao i ");
        sql.append("WHERE 1=1 ");

        if(instituicaoDTO.getIdInstituicao() != null)
            sql.append("AND i.idInstituicao = :idInstituicao ");

        if(instituicaoDTO.getNome() != null && !instituicaoDTO.getNome().isEmpty())
            sql.append("AND LOWER(i.nome) LIKE '%' || LOWER(:nome) || '%' ");

        if(instituicaoDTO.getCidade() != null && !instituicaoDTO.getCidade().isEmpty())
        	sql.append("AND LOWER(i.cidade) LIKE '%' || LOWER(:cidade) || '%' ");

        if(instituicaoDTO.getTipo() != null && !instituicaoDTO.getTipo().isEmpty())
            sql.append("AND i.tipo = :tipo ");

        Query query = getEntityManager().createQuery(sql.toString());

        if(instituicaoDTO.getIdInstituicao() != null)
           query.setParameter("idInstituicao", instituicaoDTO.getIdInstituicao());

        if(instituicaoDTO.getNome() != null && !instituicaoDTO.getNome().isEmpty())
            query.setParameter("nome", instituicaoDTO.getNome());
        
        if(instituicaoDTO.getCidade() != null && !instituicaoDTO.getCidade().isEmpty())
            query.setParameter("cidade", instituicaoDTO.getCidade());

        if(instituicaoDTO.getTipo() != null && !instituicaoDTO.getTipo().isEmpty())
            query.setParameter("tipo", instituicaoDTO.getTipo());

        return query.getResultList();
    }
	
	public List<Instituicao> listarInstituicoes() {
        return list();
    }
	

    public List<Instituicao> obterInstituicoes() {
	    return list();
    }

    public List<Instituicao> obterInstituicoesEnsino() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT i FROM Instituicao i ");
        sql.append("WHERE i.tipo = :tipo ");

        return getEntityManager().createQuery(sql.toString())
                .setParameter("tipo", TipoInstituicao.ENSINO.getDescricao())
                .getResultList();
    }

    public List<Instituicao> obterInstituicoesFinanceiras(){
	    StringBuilder sql  = new StringBuilder();
	    sql.append("SELECT i FROM Instituicao i ");
	    sql.append("WHERE i.tipo = :tipo");

	    return getEntityManager().createQuery(sql.toString())
                .setParameter("tipo", TipoInstituicao.FINANCEIRA.getDescricao())
                .getResultList();
    }

}
