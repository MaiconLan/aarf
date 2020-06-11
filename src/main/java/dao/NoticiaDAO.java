package dao;

import java.util.List;

import javax.persistence.Query;

import dto.EditalDTO;
import generics.GenericDAO;
import generics.GenericDAOV2;
import model.Noticia;

public class NoticiaDAO extends GenericDAOV2<Noticia, Long> {

    public List<Noticia> consultaNoticia(Noticia noticia) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT n FROM Noticia n ");
        sql.append("WHERE 1=1 ");

        if (noticia.getTitulo() != null && !noticia.getTitulo().isEmpty()) {
            sql.append("And n.titulo = :titulo ");
        }

        sql.append("ORDER BY n.publicacao DESC ");

        Query query = getEntityManager().createQuery(sql.toString());

        if (noticia.getTitulo() != null && !noticia.getTitulo().isEmpty()) {
            query.setParameter("titulo", noticia.getTitulo());
        }

        return query.getResultList();
    }

    public List<Noticia> consultaNoticia() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT n FROM Noticia n ");

        Query query = getEntityManager().createQuery(sql.toString());

        return query.getResultList();
    }
}
