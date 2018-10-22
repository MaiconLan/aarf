package service;

import dto.EstudanteDTO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Estudante;
import model.Usuario;

import java.io.Serializable;
import java.util.List;

public interface EstudanteService {

    void salvarEstudante(Estudante estudante) throws EstudanteBusinessException, LoginException;

    Estudante obterEstudante(Long idEstudante);

    List<Estudante> consultarEstudantes(EstudanteDTO estudanteDTO);

    boolean isLoginPreenchido(Usuario usuario);

    void removerEstudante(Estudante estudante);
}
