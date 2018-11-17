package service;

import dto.UsuarioDTO;
import exception.LoginException;
import model.Perfil;
import model.Regra;
import model.Usuario;

import javax.persistence.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UsuarioService {

    Usuario salvarUsuario(Usuario usuario) throws LoginException;

    void removerUsuario(Usuario usuario) throws NullPointerException, Exception;

    Usuario logar(Usuario usuario) throws LoginException, UnsupportedEncodingException, NoSuchAlgorithmException;

    boolean isValido(Usuario usuario) throws NullPointerException, PersistenceException, Exception;

    List<Regra> buscarRegras(Usuario usuario) throws NullPointerException, PersistenceException, Exception;

    List<Perfil> buscarPerfis() throws NullPointerException, PersistenceException, Exception;

    List<Usuario> buscarUsuarios(UsuarioDTO usuarioDTO);
}
