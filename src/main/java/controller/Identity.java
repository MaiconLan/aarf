package controller;

import exception.LoginException;
import model.Login;
import model.Regra;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import service.LoginService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named(value = "identity")
public class Identity implements Serializable {

	private static final String LOGIN_ERRO_MENSAGEM = "Erro ao efetuar login!";

	private static final String LOGIN_ERRO_DETALHES = "Verifique seu Login/Senha! Lembre-se que maiúsculas e minúsculas fazem a diferençaa.";

	private static final String ERRO_DETALHES = "Ocorreu um erro ao tentar efetuar Login.";

	private static final String AVISO_DESLOGADO = "Você deve estar logado para efetuar esta ação.";

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService service;

	private Login login;

	private boolean logado;

	private List<Regra> regras;

	@PostConstruct
	public void initialize() {
		novoLogin();
	}

	public String logar() {
		String redirect = "";
		try {

			logado = service.isValido(login);

			if (logado) {
				login = service.logar(login);
				regras = service.buscarRegras(login);
				setLoginContext("login", login);

				redirect = "index.xhtml";
			} else {
				Messages.addWarn(LOGIN_ERRO_MENSAGEM, LOGIN_ERRO_DETALHES);
			}

		} catch (LoginException e) {
			Messages.addWarn(LOGIN_ERRO_MENSAGEM, LOGIN_ERRO_DETALHES);
			e.printStackTrace();
			redirect = "";

		} catch (PersistenceException e) {
			Messages.addFlashError(LOGIN_ERRO_MENSAGEM, ERRO_DETALHES);
			e.printStackTrace();
			redirect = "";

		} catch (Exception e) {
			Messages.addError(LOGIN_ERRO_MENSAGEM, "Detalhes: " + e.getStackTrace());
			e.printStackTrace();
			redirect = "";

		} finally {
			return redirect;
		}
	}

	public void deslogar() {
		novoLogin();
		logado = false;

		try {
			Faces.invalidateSession();
			Faces.redirect("index.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean hasPermission(String permissao) {
		for (Regra regra : regras) {
			if (regra.getNome().equals(permissao)) {
				return true;
			}
		}
		return false;
	}

	private void setLoginContext(String parametro, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(parametro, object);
	}

	public String avisoDeslogado() {
		String aviso = "";

		if (!logado) {
			aviso = AVISO_DESLOGADO;
		}

		return aviso;
	}

	public void novoLogin() {
		regras = new ArrayList<>();
		login = new Login();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

}
