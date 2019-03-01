package controller;

import exception.LoginException;
import exception.UsuarioException;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named(value = "recuperacaoSenhaMB")
public class RecuperacaoSenhaMB implements Serializable {

    private static final long serialVersionUID = 7864075731067656231L;

    @Inject
    private UsuarioService usuarioService;

    private String cpf;

    private String email;

    public void recuperarSenha(){
        try {
            usuarioService.recuperarSenha(email, cpf);
            Messages.addInfo(null, "Recuperação de senha realizada com sucesso. Verifique sua caixa de entrada com a nova senha");
        } catch (UsuarioException | LoginException e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
