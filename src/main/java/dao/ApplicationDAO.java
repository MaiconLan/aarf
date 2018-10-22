package dao;

import generics.GenericDAO;

import javax.persistence.Query;

public class ApplicationDAO extends GenericDAO<String> {

    public String getVersao() {
        try {
            String hql = "SELECT version FROM public.versao_base ORDER BY installed_rank DESC LIMIT 1;";
            Query query = em.createNativeQuery(hql);
            return (String) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}