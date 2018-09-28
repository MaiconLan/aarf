package dao;

import dto.UsuarioDTO;
import generics.GenericDAO;
import model.Perfil;
import model.Regra;
import model.Usuario;
import utils.Criptografia;

import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {

    public void save(Usuario usuario) {
        try {
            usuario.setSenha(Criptografia.criptofragar(usuario.getSenha()));

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();

        }
    }

    @Override
    public Usuario update(Usuario usuario) {
        try {
            usuario.setSenha(Criptografia.criptofragar(usuario.getSenha()));

            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();

        }
        return usuario;
    }

    @SuppressWarnings("unchecked")
    public List<Perfil> buscarPerfis() {

        //return em.createNamedQuery(Perfil.BUSCAR_PERFIS).getResultList();
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarLogins() {

        //return em.createNamedQuery(Usuario.).getResultList();
        return null;
    }

    @SuppressWarnings("unchecked")

    public List<Regra> buscarRegras(Usuario usuario) {
        //return em.createNamedQuery(Regra.BUSCAR_REGRAS_BY_LOGIN2)
        //       .setParameter("idLogin", usuario.getIdLogin())
        //        .getResultList();
        return null;
    }

    public Usuario dadosUsuario(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        /*
        String hql = "FROM Usuario u WHERE login = :login AND senha = :senha ";

        Query query = em.createQuery(hql);
        query.setParameter("login", usuario.getLogin());
        query.setParameter("senha", criptofragar(usuario.getSenha()));

        return (Usuario) query.getSingleResult();
        */
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarLogins(UsuarioDTO usuarioDTO) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT l FROM Usuario l ");
        hql.append("JOIN l.colaborador c ");

        populaFiltros(usuarioDTO, hql);

        hql.append(" ORDER BY l.idLogin");

        Query query = em.createQuery(hql.toString()).setMaxResults(100);
        return query.getResultList();
    }

    public void populaFiltros(UsuarioDTO usuarioDTO, StringBuilder hql) {
        hql.append(" WHERE");
/*
        if (Comparacao.isNotNull(loginDTO.getIdLogin())) {
            hql.append(" c.idLogin = ");
            hql.append(loginDTO.getIdLogin());
            hql.append(" AND");
        }
        if (Comparacao.isNotNull(loginDTO.getIdColaborador())) {
            hql.append(" LOWER(c.idColaborador) = ");
            hql.append(loginDTO.getIdColaborador());
            hql.append(" AND");
        }
        hql.append(" c.idColaborador IS NOT NULL");
        */
    }

    public boolean possuiUsuario(Usuario usuario){
        /*
        boolean possuiIdLogin = usuario.getIdLogin() != null;

        Query query = em.createNativeQuery(sqlVerificarUsuarioJaCadastrado(possuiIdLogin));
        query.setParameter("usuario", usuario.getUsuario());

        if(possuiIdLogin)
            query.setParameter("idLogin", usuario.getIdLogin());

        return (boolean) query.getSingleResult();
        */
        return false;
    }

    private String sqlVerificarUsuarioJaCadastrado(boolean possuiIdLogin){
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(l) > 0 ");
        sql.append(" 	FROM Usuario l ");
        sql.append(" WHERE l.usuario = :usuario ");

        if(possuiIdLogin)
            sql.append(" 	AND l.id_login <> :idLogin ");

        sql.append(";");

        return sql.toString();
    }

}
