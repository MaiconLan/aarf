package dao;

import java.util.List;

import javax.persistence.Query;

import generics.GenericDAO;
import model.Anexo;
import model.Noticia;

public class AnexoDAO extends GenericDAO<Anexo> {

    public void removerMatriculaAnexo(Anexo anexo, Long idMatricula) {
        String deletarVinculo = "DELETE FROM matricula.matriculas_anexos WHERE id_anexo = :idAnexo AND id_matricula = :idMatricula";
        String deletarAnexo = "DELETE FROM cadastro.anexo WHERE id_anexo = :idAnexo";

        em.getTransaction().begin();

        em.createNativeQuery(deletarVinculo)
                .setParameter("idAnexo", anexo.getIdAnexo())
                .setParameter("idMatricula", idMatricula)
                .executeUpdate();

        em.createNativeQuery(deletarAnexo)
                .setParameter("idAnexo", anexo.getIdAnexo())
                .executeUpdate();
        em.getTransaction().commit();
    }
}
