package controller;

import dto.BancoDTO;
import model.Banco;
import org.primefaces.context.RequestContext;
import service.BancoService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("bancoMB")
public class BancoMB implements Serializable{

	private static final long serialVersionUID = 1L;

	private Banco banco;
	
	private BancoDTO bancoDTO = new BancoDTO();
	
	private List<Banco> bancos;

	@Inject
	private BancoService bancoService;
	
	@PostConstruct
    public void init(){
        novoBanco();
    }
	
	public void selecionarBanco(Banco banco) {
        setBanco(banco);
        RequestContext.getCurrentInstance().execute("PF('modalConsultaBanco').hide();");
		RequestContext.getCurrentInstance().update("formBanco");
    }
	
	public void consultarBanco() {
		bancos = bancoService.consultaBanco(bancoDTO);
	}
	
	public void modalConsultaBanco() {
		bancos = new ArrayList<>();
		RequestContext.getCurrentInstance().update("PF('modalConsultaBanco').show();");
	}
	
	public void salvarBanco() {
		bancoService.salvar(banco);
		limparCampos();
	}
	
	public void limparCampos() {
		novoBanco();
	}
	
	public void novoBanco() {
		banco = new Banco();
	}


	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public BancoDTO getBancoDTO() {
		return bancoDTO;
	}

	public void setBancoDTO(BancoDTO bancoDTO) {
		this.bancoDTO = bancoDTO;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}
}
