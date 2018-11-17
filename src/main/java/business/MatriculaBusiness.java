package business;

import dao.MatriculaDAO;
import dao.ViagemDAO;
import model.Matricula;
import model.Viagem;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

public class MatriculaBusiness {

    @Inject
    private MatriculaDAO matriculaDAO;

    @Inject
    private ViagemDAO viagemDAO;

    public void salvar(Matricula matricula) {
        matricula.setInscricao(LocalDateTime.now());
        if(matricula.getIdMatricula() == null)
            matriculaDAO.save(matricula);
        else
            matriculaDAO.update(matricula);
    }

    public void salvarViagem(Viagem viagem) {
        viagemDAO.save(viagem);
    }
}
