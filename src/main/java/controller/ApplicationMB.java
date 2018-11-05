package controller;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import service.ApplicationServiceImpl;
import utils.Constantes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named(value = "applicationMB")
public class ApplicationMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String versao;
    private String direitos;
    public static final String SECURITY_PATH = "/security/";

    @Inject
    private ApplicationServiceImpl service;

    @PostConstruct
    public void initialize() {
        direitos = Constantes.DIREITOS;
        versao = service.getVersaoCompleta();
    }

    public void ausente() {
        Messages.addWarn(null, "Você ficou muito tempo ausente. Se esta mensagem permanecer atualize a página!");
    }

    public void online() {
        Messages.addWarn(null, "Bem-vindo(a) de volta!");
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    public String getSecurityPath() {
        return SECURITY_PATH;
    }

    public void setIdSize() {
    }

    public void setInputSize() {
    }

    public String getDireitos() {
        return direitos;
    }

    public void setDireitos(String direitos) {
        this.direitos = direitos;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

}
