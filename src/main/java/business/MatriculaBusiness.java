package business;

import dao.MatriculaDAO;
import dao.ViagemDAO;
import model.Cancelamento;
import model.Edital;
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
    
    public void autorizaMatricula(Matricula m) {
    	m.setConfirmacao(LocalDateTime.now());
    	matriculaDAO.update(m);
    }
    
    public void recusarMatricula(Matricula m, String motivo) {
        Cancelamento cancelamento = new Cancelamento();
        cancelamento.setMotivo(motivo);
        cancelamento.setCancelamento(LocalDateTime.now());
        m.setCancelamento(cancelamento);
        cancelamento.setMatricula(m);
    	matriculaDAO.update(m);
    }
 
    public List<Matricula> listarMatricula(Matricula m) {
       return matriculaDAO.buscarMatriculas(m);
    }

    public Matricula obterMatricula(Long idEstudante) {
        return matriculaDAO.obterMatricula(idEstudante);
    }
}
