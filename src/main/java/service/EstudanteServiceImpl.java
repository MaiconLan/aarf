package service;

import business.EstudanteBusiness;
import exception.EstudanteBusinessException;
import model.Estudante;

import javax.inject.Inject;
import java.util.List;

public class EstudanteServiceImpl implements EstudanteService {

    private static final long serialVersionUID = 1830819295361181977L;

    @Inject
    private EstudanteBusiness estudanteBusiness;

    @Override
    public void salvarEstudante(Estudante estudante) throws EstudanteBusinessException {
        estudanteBusiness.salvarEstudante(estudante);
    }

    @Override
    public Estudante obterEstudante(Long idEstudante) {
        return null;
    }

    @Override
    public List<Estudante> consultarEstudantesPorNome(String nome) {
        return null;
    }
}
