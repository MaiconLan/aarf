package controller;

import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Endereco;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import service.AssociadoService;
import service.CepService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("associadoMB")
public class AssociadoMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Associado associado;

    @Inject
    private AssociadoService service;
    
    @Inject
    private CepService cepService;

    @PostConstruct
    public void init(){
    	associado = new Associado();
    }

    public void salvarAssociado(){
        try {
        	service.salvarAssociado(associado);
            Messages.addInfo(null, "Estudante salvo com sucesso");

        } catch (AssociadoBusinessException | LoginException e) {
            Messages.addError(null, e.getMessage());
        }
    }
    
    public void consultarCep(){
        String cep = associado.getPessoa().getEndereco().getCep();
        Long idEndereco = associado.getPessoa().getEndereco().getIdEndereco();
        if(cep != null && !cep.isEmpty()){
            Endereco endereco = cepService.getEnderecoCompleto(cep);
            endereco.setIdEndereco(idEndereco);
            endereco.setPessoa(associado.getPessoa());
            associado.getPessoa().setEndereco(endereco);
        }
    }
    
    public void modalConsultaAssociado() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaAssociado').show();");
    }

    public void excluirAssociado(){
        Messages.addWarn(null, "Estudante excluido com sucesso");
    }

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
}
