package controller;

import business.InstituicaoBusiness;
import dto.InstituicaoDTO;
import exception.InstituicaoBusinessException;
import model.Cidade;
import model.Instituicao;
import model.TipoInstituicao;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("instituicaoMB")
public class InstituicaoMB implements Serializable{

	private static final long serialVersionUID = 1L;

	private Instituicao instituicao;
	
	private InstituicaoDTO instituicaoDTO = new InstituicaoDTO();

	private List<Cidade> cidades = new ArrayList<>();

	private List<Instituicao> instituicoes;
	
	@Inject
	private InstituicaoBusiness instituicaoBusiness;

	@PostConstruct
    public void init(){
        novaInstituicao();
		carregarCidades();
    }

	public void selecionarInstituicao(Instituicao instituicao) {
        setInstituicao(instituicao);

        RequestContext.getCurrentInstance().execute("PF('modalConsultaInstituicao').hide();");
        RequestContext.getCurrentInstance().update("formInstituicao");
    }

    public List<String> getTipos(){
		List<String> tipos = new ArrayList();
		tipos.add(TipoInstituicao.FINANCEIRA.getDescricao());
		tipos.add(TipoInstituicao.ENSINO.getDescricao());
		return tipos;
	}

    private void carregarCidades(){
		cidades = instituicaoBusiness.obterCidades();
	}

	public void consultarInstituicao() {
		instituicoes = instituicaoBusiness.consultarInstituicoes(instituicaoDTO);
	}
	
	public void modalConsultaInstituicao() {
		RequestContext.getCurrentInstance().execute("PF('modalConsultaInstituicao').show();");
	}
	
	public void salvarInstituicao() {
		try {
			instituicaoBusiness.salvar(instituicao);

			Messages.addInfo(null, "Instituição salva com sucesso");
			limparCampos();
		} catch (InstituicaoBusinessException e) {
			Messages.addWarn(null, e.getMessage());
		}
	}
	
	public void limparCampos() {
		novaInstituicao();
	}
	
	public void novaInstituicao() {
		instituicao = new Instituicao();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public InstituicaoDTO getInstituicaoDTO() {
		return instituicaoDTO;
	}

	public void setInstituicaoDTO(InstituicaoDTO instituicaoDTO) {
		this.instituicaoDTO = instituicaoDTO;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
