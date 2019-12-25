package dao;

import generics.GenericDAO;
import model.Contrato;

import javax.persistence.NoResultException;

public class ContratoDAO extends GenericDAO<Contrato> {

    public boolean possuiContratoVigente(Long idContrato) {
        String query = "SELECT EXISTS( " +
                "SELECT 1 FROM financeiro.contrato c WHERE current_date BETWEEN c.inicio AND c.termino AND c.id_contrato <> :idContrato)";
        return (boolean) em.createNativeQuery(query)
                .setParameter("idContrato", idContrato)
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
