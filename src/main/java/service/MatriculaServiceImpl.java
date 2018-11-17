package service;

import business.MatriculaBusiness;
import model.Matricula;
import model.Viagem;
import service.MatriculaService;

import javax.inject.Inject;
import java.io.Serializable;

public class MatriculaServiceImpl implements MatriculaService, Serializable {

    private static final long serialVersionUID = -5431634182682857354L;

    @Inject
    private MatriculaBusiness matriculaBusiness;

	@Override
	public void salvar(Matricula matricula) {
        matriculaBusiness.salvar(matricula);
	}

    @Override
    public void salvarViagem(Viagem viagem) {
        matriculaBusiness.salvarViagem(viagem);
    }
}
