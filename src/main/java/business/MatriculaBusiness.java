package business;

import dao.MatriculaDAO;
import dao.ViagemDAO;
import enumered.MatriculaSituacao;
import exception.MatriculaBusinessException;
import model.Cancelamento;
import model.Matricula;
import model.Viagem;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MatriculaBusiness {

    @Inject
    private MatriculaDAO matriculaDAO;

    @Inject
    private ViagemDAO viagemDAO;

    public void salvar(Matricula matricula) {
        matricula.setInscricao(LocalDateTime.now());
        matricula.setDataSituacao(LocalDateTime.now());
        matricula.setSituacao(MatriculaSituacao.INSCRICAO.getDescricao());
        if(matricula.getIdMatricula() == null)
            matriculaDAO.save(matricula);
        else
            matriculaDAO.update(matricula);
    }

    public void salvarViagem(Viagem viagem) {
        viagemDAO.save(viagem);
    }

    public void autorizaMatricula(Matricula m) {
    	m.setSituacao(MatriculaSituacao.MATRICULADO.getDescricao());
    	m.setDataSituacao(LocalDateTime.now());
    	matriculaDAO.update(m);
    }
    
    public void cancelarMatricula(Matricula m, String motivo) {
        Cancelamento cancelamento = new Cancelamento();
        cancelamento.setMotivo(motivo);
        cancelamento.setCancelamento(LocalDateTime.now());
        m.setSituacao(MatriculaSituacao.CANCELADO.getDescricao());
        m.setDataSituacao(LocalDateTime.now());
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

    public void enviarParaAprovacao(Matricula matricula) throws MatriculaBusinessException {
        validarEnviarParaAprovacao(matricula);

        matricula.setSituacao(MatriculaSituacao.EM_APROVACAO.getDescricao());
        matricula.setDataSituacao(LocalDateTime.now());
        matriculaDAO.update(matricula);
    }

    private void validarEnviarParaAprovacao(Matricula matricula) throws MatriculaBusinessException {
        Collection<String> detalhes = new ArrayList<>();

        if(matricula.getAnexos().isEmpty())
            detalhes.add("É necessário pelo menos um comprovante de estudo!");

        if(matricula.getViagens().isEmpty())
            detalhes.add("É necessário pelo menos uma viagem selecionada para se inscrever!");

        if(!detalhes.isEmpty())
            throw new MatriculaBusinessException(detalhes);
    }
}
