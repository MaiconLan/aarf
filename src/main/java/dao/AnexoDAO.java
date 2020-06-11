package dao;

import generics.GenericDAOV2;
import model.Anexo;

public class AnexoDAO extends GenericDAOV2<Anexo, Long> {

    public void removerMatriculaAnexo(Anexo anexo, Long idMatricula) {
        String deletarVinculo = "DELETE FROM matricula.matriculas_anexos WHERE id_anexo = :idAnexo AND id_matricula = :idMatricula";
        String deletarAnexo = "DELETE FROM cadastro.anexo WHERE id_anexo = :idAnexo";

        getEntityManager().getTransaction().begin();

        getEntityManager().createNativeQuery(deletarVinculo)
                .setParameter("idAnexo", anexo.getIdAnexo())
                .setParameter("idMatricula", idMatricula)
                .executeUpdate();

        getEntityManager().createNativeQuery(deletarAnexo)
                .setParameter("idAnexo", anexo.getIdAnexo())
                .executeUpdate();
        getEntityManager().getTransaction().commit();
    }
}
