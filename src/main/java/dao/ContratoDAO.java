package dao;

import generics.GenericDAO;
import model.Contrato;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ContratoDAO extends GenericDAO<Contrato> {

    public boolean possuiContratoVigente(Contrato contrato) {
        String sql = "SELECT EXISTS( " +
                "SELECT 1 FROM financeiro.contrato c " +
                "WHERE (:inicio BETWEEN c.inicio AND c.termino " +
                "OR :termino BETWEEN c.inicio AND c.termino) ";

        if (contrato.getIdContrato() != null)
            sql += "AND c.id_contrato <> :idContrato ";

        sql += ")";

        Query query = em.createNativeQuery(sql);

        if (contrato.getIdContrato() != null)
            query.setParameter("idContrato", contrato.getIdContrato());

        return (boolean) query
                .setParameter("inicio", contrato.getInicio())
                .setParameter("termino", contrato.getTermino())
                .getSingleResult();
    }

    public Contrato obterContratoVigente() {
        String query = "SELECT c FROM Contrato c WHERE current_date BETWEEN c.inicio AND c.termino";

        try {
            return (Contrato) em.createQuery(query)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
}
