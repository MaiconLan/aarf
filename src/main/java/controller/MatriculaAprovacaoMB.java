package controller;


import dto.FiltroViagemDTO;
import model.Instituicao;
import model.Matricula;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import service.InstituicaoService;
import service.MatriculaService;
import service.ViagemService;
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
	private MatriculaService matriculaService;
	
	@Inject
	private ViagemService viagemService;
    
    @Inject
    private InstituicaoService instituicaoService;
	
    private List<Instituicao> instituicoes;
	private List<Matricula> listMatricula;
	private Matricula filtro;
	private Matricula matriculaSelecionada;
	private String motivoCancelamento;
	
	@PostConstruct
	public void init() {
		limpar();
	}
	
	public void limpar() {
		this.matriculaSelecionada = new Matricula();
		this.listMatricula = new ArrayList<>();
		this.filtro = new Matricula();
		carregarInstituicoes();
		buscarMatricula();
	}
	
	public void reprovarMatricula() {
		matriculaService.cancelarMatricula(matriculaSelecionada, motivoCancelamento);
		enviarEmail(matriculaSelecionada);
		Messages.addInfo(null, "Matricula reprovada com sucesso!");
		buscarMatricula();
		fecharModalReprovarMatricula();
	}

	public void abrirModalReprovarMatricula(Matricula matricula){
		matriculaSelecionada = matricula;
		RequestContext.getCurrentInstance().execute("PF('modalReprovarMatricula').show();");
		RequestContext.getCurrentInstance().update("modalReprovarMatricula");
	}

	public void fecharModalReprovarMatricula(){
		limpar();
		RequestContext.getCurrentInstance().execute("PF('modalReprovarMatricula').hide();");
		RequestContext.getCurrentInstance().update("modalReprovarMatricula");
	}

	public void aprovarMatricula(Matricula matricula) {
		matriculaService.aprovarMatricula(matricula);
		enviarEmail(matricula);
		Messages.addInfo(null, "Matricula aprovada com sucesso!");
		buscarMatricula();
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
        instituicoes = instituicaoService.obterInstituicoesEnsino();
        buscarMatricula();
    }
	
	public void consultarDetalhes(Matricula m) {
		this.matriculaSelecionada = m;
		
		FiltroViagemDTO filtroViagemDTO = new FiltroViagemDTO();
		filtroViagemDTO.setIdEdital(m.getEdital().getIdEdital());		
		filtroViagemDTO.setIdMatricula(m.getIdMatricula());		
		
		this.matriculaSelecionada.setViagens(viagemService.buscarViagens(filtroViagemDTO));
		RequestContext.getCurrentInstance().update("modalDetalhesEstudante");
		RequestContext.getCurrentInstance().execute("PF('modalDetalhesEstudante').show();");
	}
	
	public void buscarMatricula(){
		 this.listMatricula = matriculaService.listarMatriculas(this.filtro);
	}

	public List<Matricula> getListMatricula() {
		return listMatricula;
	}

	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}

	public Matricula getFiltro() {
		return filtro;
	}

	public void setFiltro(Matricula filtro) {
		this.filtro = filtro;
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
