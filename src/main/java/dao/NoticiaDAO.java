package dao;

import java.util.List;

import javax.persistence.Query;

import dto.EditalDTO;
import generics.GenericDAO;
import model.Noticia;

public class NoticiaDAO extends GenericDAO<Noticia> {
	 public List<Noticia> consultaNoticia(Noticia noticia) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT n FROM Noticia n "); 
	        sql.append("WHERE 1=1 ");
	        
	        if (noticia.getTitulo() != null && !noticia.getTitulo().isEmpty()) {
	            sql.append("And n.titulo = :titulo ");
	        }
	        
	        sql.append("ORDER BY n.publicacao DESC ");

	        Query query = em.createQuery(sql.toString());
	        
	        if (noticia.getTitulo() != null && !noticia.getTitulo().isEmpty()) {
	            query.setParameter("titulo", noticia.getTitulo());
	        }
	        
	        return query.getResultList();
	    }
	 
	 public List<Noticia> consultaNoticia() {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT n FROM Noticia n "); 
	        
	        Query query = em.createQuery(sql.toString());
	        
	        
	        return query.getResultList();
	    }
}
