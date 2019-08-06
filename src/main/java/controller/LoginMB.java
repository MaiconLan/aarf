package controller;

import business.UsuarioBusiness;
import exception.LoginException;
import model.Regra;
import model.Usuario;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named(value = "loginMB")
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 5951997907554897426L;

    private static final String LOGIN_ERRO_DETALHES = "Verifique seu Login/Senha! Lembre-se que maiúsculas e minúsculas fazem a diferença.";

    @Inject
    private UsuarioBusiness usuarioBusiness;

    @Inject
    private Identity identity;

    private Usuario usuario;

    private List<Regra> regras;

    @PostConstruct
    public void init() {
        try {
            if (identity.isLogado()) {
                Faces.redirect("security/dashboard.xhtml");

            } else {
                usuario = new Usuario();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logar() {
        String redirect = "";
        try {

            boolean logado = usuarioBusiness.isValido(usuario);

            if (logado) {
                usuario = usuarioBusiness.logar(usuario);
                regras = usuarioBusiness.buscarRegras(usuario);
                adicionarParametroSessao("usuario", usuario);

                identity.setUsuario(usuario);
                identity.setLogado(logado);
                redirect = "security/dashboard.xhtml";
            } else {
                Messages.addWarn(null, LOGIN_ERRO_DETALHES);
            }

            Faces.redirect(redirect);

        } catch (LoginException e) {
            Messages.addWarn(null, LOGIN_ERRO_DETALHES);
            e.printStackTrace();

        } catch (Exception e) {
            Messages.addError(null, "Detalhes: " + e.getMessage());
            e.printStackTrace();

        }
    }

    private void adicionarParametroSessao(String parametro, Object object) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(parametro, object);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
