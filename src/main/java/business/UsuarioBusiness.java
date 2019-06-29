package business;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import exception.LoginException;
import exception.UsuarioException;
import model.Perfil;
import model.Regra;
import model.Usuario;
import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Messages;
import utils.Criptografia;
import utils.email.Email;
import utils.StringUtils;
import utils.email.EmailHtml;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@Stateless
public class UsuarioBusiness implements Serializable {

    private static final long serialVersionUID = -6830314258195136090L;

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

    public Usuario logar(Usuario usuario) throws LoginException, UnsupportedEncodingException, NoSuchAlgorithmException {
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

    public void recuperarSenha(String email, String cpf) throws UsuarioException, LoginException {
        if(!(email != null && cpf != null) && !(email.isEmpty() && cpf.isEmpty()))
            throw new UsuarioException("É necessário informar o CPF ou E-mail para recuperar a senha");

        cpf = StringUtils.removerCaracteres(cpf);
        Usuario usuario = usuarioDAO.buscarUsuarioByCpfEmail(email, cpf);

        if(usuario == null)
            throw new UsuarioException("Usuário com este CPF ou E-mail não encontrado");

        String senha = UUID.randomUUID().toString().substring(0, 7);
        String titulo = "Recuperação de login e senha";
        String mensagem = "Olá, seu login é <strong>" + usuario.getLogin() + "</strong> e sua nova senha é <strong>" + senha + "</strong>. " +
                        "Para manter a segurança dos seus dados, altere a sua senha ao realizar o login novamente.";
        String destinatario = usuario.getEstudante().getPessoa().getEmail();

        usuario.setSenha(senha);
        usuario.setAlterarLogin(true);
        salvar(usuario);

        new EmailHtml(titulo, mensagem, destinatario).enviar();
    }
}
