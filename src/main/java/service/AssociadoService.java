package service;

import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Usuario;

import java.io.Serializable;
import java.util.List;

import dto.AssociadoDTO;

public interface AssociadoService extends Serializable {

    void salvarAssociado(Associado associado) throws AssociadoBusinessException, LoginException;

    Associado obterAssociado(Long idAssociado);

    List<Associado> consultarAssociados(AssociadoDTO associadoDTO);

    boolean isLoginPreenchido(Usuario usuario);
    
    void removerAssociado(Associado assocaido);
}
