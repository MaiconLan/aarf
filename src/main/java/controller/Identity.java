package controller;

import business.UsuarioBusiness;
import enumered.CargoEnum;
import exception.LoginException;
import model.Associado;
import model.Estudante;
import model.Regra;
import model.Usuario;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named(value = "identity")
public class Identity implements Serializable {

	private static final long serialVersionUID = 3570532101723065655L;

	private Usuario usuario;

	private boolean logado;

	private List<Regra> regras;

	@PostConstruct
	public void initialize() {
		novoLogin();
	}

	public void novoLogin() {
		regras = new ArrayList<>();
		usuario = new Usuario();
	}

	public String obterCargo(){
		Associado associado = usuario.getAssociado();

		if(isUsuarioAssociado())
			return associado.getCargo();
		else if(isUsuarioEstudante())
			return "Estudante";

		return "";
	}

	public boolean isUsuarioAdministrador(){
		return obterCargo().equals(CargoEnum.ADMINISTRADOR.getDescricao());
	}

	public String obterNome(){
		String nome = "";

		if(usuario == null)
			return nome;

		Associado associado = usuario.getAssociado();
		Estudante estudante = usuario.getEstudante();

		if(isUsuarioAssociado())
			nome = associado.getPessoa().getPrimeiroNome();
		else if(isUsuarioEstudante())
			nome = estudante.getPessoa().getPrimeiroNome();

		return nome;
	}

	public void deslogar() {
		logado = false;

		try {
			Faces.invalidateSession();
			Faces.redirect("/aarf");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acessarPerfil() {
		Associado associado = usuario.getAssociado();
		Estudante estudante = usuario.getEstudante();
		try {
			String url = "";

			if(isUsuarioAssociado())
				url = "security/cadastros/associado/cadastro-associado.xhtml?idAssociado=" + associado.getIdAssociado() + "&isAcessarPerfil=true";
			else if (isUsuarioEstudante())
				url = "security/cadastros/estudante/cadastro-estudante.xhtml?idEstudante=" + estudante.getIdEstudante() + "&isAcessarPerfil=true";

			Faces.redirect(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isUsuarioAssociado(){
		return usuario.getAssociado() != null;
	}

	public boolean isUsuarioEstudante(){
		return usuario.getEstudante() != null;
	}

	public boolean hasPermission(String permissao) {
		for (Regra regra : regras) {
			if (regra.getNome().equals(permissao)) {
				return true;
			}
		}
		return false;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
