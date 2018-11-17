package service;

import exception.MatriculaBusinessException;
import model.Matricula;
import model.Viagem;

public interface MatriculaService {
	
	void salvar(Matricula matricula) throws MatriculaBusinessException;

	void salvarViagem(Viagem viagem);
}
