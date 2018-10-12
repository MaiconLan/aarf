package service;

import business.EstudanteBusiness;
import dto.EstudanteDTO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Estudante;
import model.Usuario;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class EstudanteServiceImpl implements EstudanteService, Serializable {

    private static final long serialVersionUID = 1830819295361181977L;

    @Inject
    private EstudanteBusiness estudanteBusiness;

    @Override
    public void salvarEstudante(Estudante estudante) throws EstudanteBusinessException, LoginException {
        estudanteBusiness.salvarEstudante(estudante);
    }

    @Override
    public Estudante obterEstudante(Long idEstudante) {
        return null;
    }

    @Override
    public List<Estudante> consultarEstudantes(EstudanteDTO estudanteDTO) {
        return estudanteBusiness.consultarEstudantes(estudanteDTO);
    }

    @Override
    public boolean isLoginPreenchido(Usuario usuario) {
        return estudanteBusiness.isLoginPreenchido(usuario);
    }

    @Override
    public void removerEstudante(Estudante estudante) {
        estudanteBusiness.removerEstudante(estudante);
    }


}
