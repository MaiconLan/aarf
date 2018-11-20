package service;

import business.MatriculaBusiness;
import model.Matricula;
import model.Viagem;
import service.MatriculaService;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

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

	@Override
	public List<Matricula> listarMatriculas(Matricula m) {
		return matriculaBusiness.listarMatricula(m);
	}

	@Override
	public Matricula obterMatricula(Long idEstudante) {
		return matriculaBusiness.obterMatricula(idEstudante);
	}

	@Override
	public void aprovarMatricula(Matricula m) {
		matriculaBusiness.autorizaMatricula(m);
	}

	@Override
	public void recusarMatricula(Matricula m) {
		matriculaBusiness.recuarMatricula(m);
	}
}
