package service;

import exception.MatriculaBusinessException;
import model.Matricula;
import model.Viagem;

import java.util.List;

public interface MatriculaService {
	
	void salvar(Matricula matricula) throws MatriculaBusinessException;

	void salvarViagem(Viagem viagem);
	
	void aprovarMatricula(Matricula m);
	
	void cancelarMatricula(Matricula m, String motivo);
	
	List<Matricula> listarMatriculas(Matricula m);

    Matricula obterMatricula(Long idEstudante);

	void enviarParaAprovacao(Matricula matricula);
}
