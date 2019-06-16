package controller;


import business.InstituicaoBusiness;
import business.MatriculaBusiness;
import business.ViagemBusiness;
import dto.FiltroViagemDTO;
import dto.MatriculaDTO;
import model.Anexo;
import model.Estudante;
import model.Instituicao;
import model.Matricula;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import utils.EmailUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "matriculaAprovacaoMB")
public class MatriculaAprovacaoMB implements Serializable {
	private static final long serialVersionUID = -7785394172005232068L;

	@Inject
	private MatriculaBusiness matriculaBusiness;
	
	@Inject
	private ViagemBusiness viagemBusiness;
    
    @Inject
    private InstituicaoBusiness instituicaoBusiness;
	
    private List<Instituicao> instituicoes;
	private List<Matricula> listMatricula;
	private MatriculaDTO matriculaDTO;
	private Matricula matriculaSelecionada;
	private String motivoCancelamento;
	
	@PostConstruct
	public void init() {
		limpar();
	}

	public void acessarEstudante(Matricula matricula) {
		Estudante estudante = matricula.getEstudante();
		try {
			Faces.redirect("security/cadastros/estudante/cadastro-estudante.xhtml?idEstudante=" + estudante.getIdEstudante() + "&isAcessarPerfil=false");

		} catch (IOException e) {
			e.printStackTrace();
			Messages.addError(null, "Erro ao redirecionar para o cadastro do estudante!");
		}
	}
	
	public void limpar() {
		this.matriculaSelecionada = new Matricula();
		this.listMatricula = new ArrayList<>();
		this.matriculaDTO = new MatriculaDTO();
		carregarInstituicoes();
	}
	
	public void reprovarMatricula() {
		matriculaBusiness.cancelarMatricula(matriculaSelecionada, motivoCancelamento);
		enviarEmail(matriculaSelecionada);
		Messages.addInfo(null, "Matricula reprovada com sucesso!");
		buscarMatriculas();
		fecharModalReprovarMatricula();
	}

	public void abrirModalReprovarMatricula(Matricula matricula){
		matriculaSelecionada = matricula;
		RequestContext.getCurrentInstance().execute("PF('modalReprovarMatricula').show();");
		RequestContext.getCurrentInstance().update("modalReprovarMatricula");
	}

	public void fecharModalReprovarMatricula(){
		limpar();
		buscarMatriculas();
		RequestContext.getCurrentInstance().execute("PF('modalReprovarMatricula').hide();");
		RequestContext.getCurrentInstance().update("modalReprovarMatricula");
	}

	public void aprovarMatricula(Matricula matricula) {
		matriculaBusiness.aprovarMatricula(matricula);
		enviarEmail(matricula);
		Messages.addInfo(null, "Matricula aprovada com sucesso!");
		buscarMatriculas();
		fecharModalReprovarMatricula();
	}

	private void enviarEmail(Matricula matricula){
		if(matricula.isMatriculado() && matricula.getCancelamento() == null)
			enviarEmailAprovada(matricula);
		else if(matricula.isCancelada() && matricula.getCancelamento() != null)
			enviarEmailReprovada(matricula);
	}

	private void enviarEmailReprovada(Matricula matricula) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			String mensagem = IOUtils.toString(classLoader.getResource("template_email/email_matricula_reprovada.html"), "UTF-8");
			String emailDestinatario = matricula.getEstudante().getPessoa().getEmail();

			mensagem = mensagem.replaceAll(":nomeEstudante:", matricula.getEstudante().getPessoa().getPrimeiroNome());
			mensagem = mensagem.replaceAll(":edital:", matricula.getEdital().getTitulo());
			mensagem = mensagem.replaceAll(":motivo:", matricula.getCancelamento().getMotivo());
			mensagem = mensagem.replaceAll(":endereco:", "Endereço");
			mensagem = mensagem.replaceAll(":numero:", "Número");
			mensagem = mensagem.replaceAll(":telefone:", "Telefone");

			EmailUtils.enviarHtmlEmail("AARF", mensagem, emailDestinatario);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			Messages.addWarn(null, "Ocorreu um erro ao enviar o e-mail");
			e.printStackTrace();
		}
	}

	private void enviarEmailAprovada(Matricula matricula){
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			String mensagem = IOUtils.toString(classLoader.getResource("template_email/email_matricula_aprovada.html"), "UTF-8");
			String emailDestinatario = matricula.getEstudante().getPessoa().getEmail();

			mensagem = mensagem.replaceAll(":nomeEstudante:", matricula.getEstudante().getPessoa().getPrimeiroNome());
			mensagem = mensagem.replaceAll(":edital:", matricula.getEdital().getTitulo());
			mensagem = mensagem.replaceAll(":endereco:", "Endereço");
			mensagem = mensagem.replaceAll(":numero:", "Número");
			mensagem = mensagem.replaceAll(":telefone:", "Telefone");

			EmailUtils.enviarHtmlEmail("AARF", mensagem, emailDestinatario);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			Messages.addWarn(null, "Ocorreu um erro ao enviar o e-mail");
			e.printStackTrace();
		}
	}

	private void carregarInstituicoes(){
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
    }
	
	public void consultarDetalhes(Matricula m) {
		this.matriculaSelecionada = m;
		
		FiltroViagemDTO filtroViagemDTO = new FiltroViagemDTO();
		filtroViagemDTO.setIdEdital(m.getEdital().getIdEdital());		
		filtroViagemDTO.setIdMatricula(m.getIdMatricula());		
		
		this.matriculaSelecionada.setViagens(viagemBusiness.buscarViagens(filtroViagemDTO));
		RequestContext.getCurrentInstance().update("modalDetalhesEstudante");
		RequestContext.getCurrentInstance().execute("PF('modalDetalhesEstudante').show();");
	}

	public boolean renderizarAnexo(Anexo anexo, String extensao){
		return anexo.getExtensao().equals(extensao);
	}
	
	public void buscarMatriculas(){
		 this.listMatricula = matriculaBusiness.listarMatriculasEmAprovacao(matriculaDTO);
	}

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

	public MatriculaDTO getMatriculaDTO() {
		return matriculaDTO;
	}

	public void setMatriculaDTO(MatriculaDTO matriculaDTO) {
		this.matriculaDTO = matriculaDTO;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}
}
