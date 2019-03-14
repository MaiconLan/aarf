package service;

import business.MatriculaBusiness;
import dao.MatriculaDAO;
import dto.MatriculaDTO;
import exception.MatriculaBusinessException;
import model.Matricula;
import model.Viagem;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class MatriculaServiceImpl implements MatriculaService, Serializable {

    private static final long serialVersionUID = -5431634182682857354L;

    @Inject
    private MatriculaBusiness matriculaBusiness;

    @Inject
	private MatriculaDAO matriculaDAO;

	@Override
	public void salvar(Matricula matricula) {
        matriculaBusiness.salvar(matricula);
	}

    @Override
    public void salvarViagem(Viagem viagem) {
        matriculaBusiness.salvarViagem(viagem);
    }

	@Override
	public List<Matricula> listarMatriculas(Matricula m) {
		return matriculaBusiness.listarMatricula(m);
	}

	@Override
	public List<Matricula> listarMatriculasByIdEstudante(Long idEstudante) {
		return matriculaDAO.listarMatriculasByIdEstudante(idEstudante);
	}

	@Override
	public Matricula obterMatriculaByIdEstudante(Long idEstudante) {
		return matriculaBusiness.obterMatriculaByIdEstudante(idEstudante);
	}

	@Override
	public void enviarParaAprovacao(Matricula matricula) throws MatriculaBusinessException {
		matriculaBusiness.enviarParaAprovacao(matricula);
	}

	@Override
	public List<Matricula> listarMatriculasEmAprovacao(MatriculaDTO matriculaDTO) {
		return matriculaDAO.listarMatriculasEmAprovacao(matriculaDTO);
	}

	@Override
	public Matricula obterMatriculaById(Long idMatricula) {
		return matriculaDAO.findById(idMatricula);
	}

	@Override
	public void aprovarMatricula(Matricula m) {
		matriculaBusiness.autorizaMatricula(m);
	}

	@Override
	public void cancelarMatricula(Matricula m, String motivo) {
		matriculaBusiness.cancelarMatricula(m, motivo);
	}
}
