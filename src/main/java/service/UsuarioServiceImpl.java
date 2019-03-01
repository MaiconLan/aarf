package service;

import business.UsuarioBusiness;
import dto.UsuarioDTO;
import exception.LoginException;
import exception.UsuarioException;
import model.Perfil;
import model.Regra;
import model.Usuario;
import service.UsuarioService;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

    private static final long serialVersionUID = 3913565584570888746L;

    @Inject
    private UsuarioBusiness usuarioBusiness;

    @Override
    public Usuario salvarUsuario(Usuario usuario) throws LoginException {
        return usuarioBusiness.salvar(usuario);
    }

    @Override
    public void removerUsuario(Usuario usuario) throws NullPointerException, Exception {

    }

    @Override
    public Usuario logar(Usuario usuario) throws LoginException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return usuarioBusiness.logar(usuario);
    }

    @Override
    public boolean isValido(Usuario usuario) throws LoginException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return usuarioBusiness.isValido(usuario);
    }

    @Override
    public List<Regra> buscarRegras(Usuario usuario) throws NullPointerException, PersistenceException, Exception {
        return null;
    }

    @Override
    public List<Perfil> buscarPerfis() throws NullPointerException, PersistenceException, Exception {
        return null;
    }

    @Override
    public List<Usuario> buscarUsuarios(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public void recuperarSenha(String email, String cpf) throws UsuarioException, LoginException {
        usuarioBusiness.recuperarSenha(email, cpf);
    }
}
