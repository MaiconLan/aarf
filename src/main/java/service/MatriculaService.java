package service;

import java.util.List;

import exception.MatriculaBusinessException;
import model.Edital;
import model.Matricula;
import model.Viagem;

public interface MatriculaService {
	
	void salvar(Matricula matricula) throws MatriculaBusinessException;

	void salvarViagem(Viagem viagem);
	
	void aprovarMatricula(Matricula m);
	
	void recusarMatricula(Matricula m, String motivo);
	
	List<Matricula> listarMatriculas(Matricula m);

    Matricula obterMatricula(Long idEstudante);
}
