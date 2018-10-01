package business;

import dao.EstudanteDAO;
import exception.EstudanteBusinessException;
import model.Estudante;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

@Stateless
public class EstudanteBusiness {

    @Inject
    private EstudanteDAO estudanteDAO;

    public void salvarEstudante(Estudante estudante) throws EstudanteBusinessException {
        validarEstudante(estudante);

        if(estudante.getIdEstudante() == null)
            estudanteDAO.save(estudante);
        else
            estudanteDAO.update(estudante);
    }

    private void validarEstudante(Estudante estudante) throws EstudanteBusinessException {
        Collection<String> detalhes = Collections.EMPTY_LIST;

        if(estudante.getPessoa().getNome() == null)
            detalhes.add("Nome é de preenchimento obrigatório");

        if(!detalhes.isEmpty())
            throw new EstudanteBusinessException(detalhes);
    }
}
