package business;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import exception.LoginException;
import model.Perfil;
import model.Regra;
import model.Usuario;
import org.omnifaces.util.Messages;
import utils.Criptografia;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioBusiness {


    @Inject
    private UsuarioDAO usuarioDAO;

    public Usuario salvar(Usuario usuario) throws LoginException {

        if (usuario.isAlterarLogin()) {
            validarLogin(usuario);
            if (usuario.getIdUsuario() == null)
                usuarioDAO.save(usuario);
             else
                usuarioDAO.update(usuario);
        }

        return usuario;
    }

    public Usuario logar(Usuario usuario) throws Exception {
        if (isValido(usuario)) {
            return usuarioDAO.dadosUsuario(usuario);

        } else {
            throw new LoginException();
        }
    }

    private void validarLogin(Usuario usuario) throws LoginException {

        if(usuarioDAO.possuiUsuario(usuario)){
            throw new LoginException("Este usuário já está cadastrado, insira outro nome de usuário.");
        }

        if(usuario.getLogin().contains(" ")){
            throw new LoginException("Não insira espaços em branco.");
        }
    }

    public void remover(Usuario usuario) throws NullPointerException, Exception{
        usuarioDAO.remove(usuario.getIdUsuario());
    }

    public boolean isValido(Usuario usuario) throws UnsupportedEncodingException, NoSuchAlgorithmException, LoginException {
        Usuario resultado = null;

        try {
            resultado = usuarioDAO.dadosUsuario(usuario);
        } catch (PersistenceException e) {
            throw new LoginException();
        }

        if(resultado == null)
            return false;

        return usuario.getLogin().equals(resultado.getLogin())
                && Criptografia.criptofragar(usuario.getSenha()).equals(resultado.getSenha());
    }

    public List<Regra> buscarRegras(Usuario usuario) throws NullPointerException, PersistenceException, Exception {
        return usuarioDAO.buscarRegras(usuario);
    }

    public List<Perfil> buscarPerfis() {
        return usuarioDAO.buscarPerfis();
    }

    public List<Usuario> buscarLogins() {
        return usuarioDAO.buscarLogins();
    }

    public List<Usuario> buscarLogins(UsuarioDTO loginDTO) {
        return usuarioDAO.buscarLogins(loginDTO);
    }

}
