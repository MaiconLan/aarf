package service;

import exception.MatriculaBusinessException;
import model.Matricula;

public interface MatriculaService {
	
	void salvar(Matricula matricula) throws MatriculaBusinessException;
	
}
