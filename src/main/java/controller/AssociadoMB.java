package controller;

import business.AssociadoBusiness;
import dto.AssociadoDTO;
import enumered.CargoEnum;
import exception.AssociadoBusinessException;
import exception.CepBussinesException;
import exception.LoginException;
import model.Associado;
import model.Endereco;
import model.Usuario;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.CepService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("associadoMB")
public class AssociadoMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Associado associado;

    private List<Associado> listaAssociado;

    private AssociadoDTO associadoDTO = new AssociadoDTO();

    private boolean alterarLogin;

    @Inject
    private CepService cepService;

    @Inject
    private Identity identity;
    
    @Inject
    private AssociadoBusiness associadoBusiness;

    @Inject @Param
    private Long idAssociado;

    @PostConstruct
    public void init(){
        carregarAssociado();
    }

    private void carregarAssociado(){
        if(idAssociado != null ){
            if(identity.isUsuarioAssociado())
                selecionarAssociado(associadoBusiness.obterAssociado(idAssociado));
        } else {
            novoAssociado();
        }
    }

    public void modalConsultaAssociado() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaAssociado').show();");
    }

    public void selecionarAssociado(Associado associado) {
        novoAssociado();
        setAssociado(associado);
        if (associado.getUsuario() == null) {
        	associado.setUsuario(new Usuario());
        }
        if(associado.getPessoa().getEndereco() == null){
        	associado.getPessoa().setEndereco(new Endereco());
        }

        setAlterarLogin(!associadoBusiness.isLoginPreenchido(associado.getUsuario()));

        RequestContext.getCurrentInstance().execute("PF('modalConsultaAssociado').hide();");
        RequestContext.getCurrentInstance().update("form");
        RequestContext.getCurrentInstance().update("panelUsuario");
    }

    public void salvarAssociado(){
        try {
            associado.getUsuario().setAlterarLogin(alterarLogin);
            associadoBusiness.salvarAssociado(associado);
            Messages.addInfo(null, "Associado salvo com sucesso");
            novoAssociado();

        } catch (AssociadoBusinessException | LoginException e) {
            e.getMessages().forEach(mensagem -> Messages.addError(null, mensagem));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerAssociado(){
        associadoBusiness.removerAssociado(associado);
        Messages.addInfo(null, "Associado removido com sucesso");
        novoAssociado();
    }

    public void consultarAssociado(){
        listaAssociado = associadoBusiness.consultarAssociados(associadoDTO);
    }

    public boolean isDesabilitarAlteracaoLogin(){
        return (associado.getUsuario().getIdUsuario() != null && !alterarLogin);
    }

    public void consultarCep(){
        String cep = associado.getPessoa().getEndereco().getCep();
        Long idEndereco = associado.getPessoa().getEndereco().getIdEndereco();
        if(cep != null && !cep.isEmpty()){
            try {
                Endereco endereco = cepService.getEnderecoCompleto(cep);
                endereco.setIdEndereco(idEndereco);
                endereco.setPessoa(associado.getPessoa());
                associado.getPessoa().setEndereco(endereco);
            } catch (CepBussinesException e) {
                Messages.addWarn(null, e.getMessage());
            }
        }
    }
    
    public  CargoEnum[] getCargos(){
		return CargoEnum.values();
		
	}

    public void toggleAlterarLogin(){
        setAlterarLogin(!alterarLogin);
    }

    public boolean renderizaAlterarLogin(){
        return associado.getIdAssociado() != null;
    }

    private void novoAssociado(){
        associado = new Associado();
        setAlterarLogin(true);
    }

    public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public List<Associado> getListaAssociado() {
		return listaAssociado;
	}

	public void setListaAssociado(List<Associado> listaAssociado) {
		this.listaAssociado = listaAssociado;
	}

	public boolean isAlterarLogin() {
        return alterarLogin;
    }

	public AssociadoDTO getAssociadoDTO() {
		return associadoDTO;
	}

	public void setAssociadoDTO(AssociadoDTO associadoDTO) {
		this.associadoDTO = associadoDTO;
	}

	public void setAlterarLogin(boolean alterarLogin) {
        this.alterarLogin = alterarLogin;
    }
}
