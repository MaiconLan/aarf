package controller;

import exception.CepBussinesException;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Endereco;
import model.Estudante;
import model.Instituicao;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Messages;
import service.CepService;
import utils.EmailUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("usuarioMB")
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 8382542571692876048L;

    private Estudante estudante;

    private List<Instituicao> instituicoes;

    @Inject
    private EstudanteService estudanteService;

    @Inject
    private CepService cepService;

    @Inject
    private InstituicaoService instituicaoService;

    @PostConstruct
    public void init(){
        novoEstudante();
        carregarInstituicoes();
    }

    private void carregarInstituicoes(){
        instituicoes = instituicaoService.obterInstituicoesEnsino();
    }

    public void salvarEstudante(){
        try {
            estudanteService.salvarEstudante(estudante);
            Messages.addInfo(null, "Bem-vindo " + estudante.getPessoa().getNome());

            enviarEmail();

        } catch (EstudanteBusinessException | LoginException e) {
            Messages.addError(null, e.getMessage());
        }
    }

    public void consultarCep(){
        String cep = estudante.getPessoa().getEndereco().getCep();
        Long idEndereco = estudante.getPessoa().getEndereco().getIdEndereco();
        if(cep != null && !cep.isEmpty()){
            try {
                Endereco endereco = cepService.getEnderecoCompleto(cep);
                endereco.setIdEndereco(idEndereco);
                endereco.setPessoa(estudante.getPessoa());
                estudante.getPessoa().setEndereco(endereco);
            } catch (CepBussinesException e) {
                Messages.addWarn(null, e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private void novoEstudante(){
        estudante = new Estudante();
        estudante.getUsuario().setAlterarLogin(true);
    }

    private void enviarEmail(){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String mensagem = IOUtils.toString(classLoader.getResource("template_email/email_cadastro_usuario.html"), "UTF-8");
            String emailDestinatario = estudante.getPessoa().getEmail();
            String genero =  estudante.getPessoa().getGenero();
            String recepcao = genero.equals("M") ? "Bem-Vindo": genero.equals("F") ? "Bem-Vinda" : "Bem-Vindo(a)";

            mensagem = mensagem.replaceAll(":recepcao:", recepcao);
            mensagem = mensagem.replaceAll(":nomeEstudante:", estudante.getPessoa().getPrimeiroNome());
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

    private void enviarEmailSimples(){
        try {
            String emailDestinatario = estudante.getPessoa().getEmail();
            String genero =  estudante.getPessoa().getGenero();
            String recepcao = genero.equals("M") ? "Bem-Vindo": genero.equals("F") ? "Bem-Vinda" : "Bem-Vindo(a)";
            String mensagem = "Olá :nomeEstudante:. "
                    + "<br/> Seja :recepcao: à AARF. <br/>"
                    + "Agora você possui acesso ao sistema da associação. Sinta-se a vontade para realizar a inscrição nos editais disponíveis.<br/>"
                    + "Caso necessite alterar a senha, você poderá realizar pela tela de login. Você pode conferir suas informações em seu Perifl.<br/>"
                    + "Você receberá por e-mail novos comunicados, editais disponíveis e notícias.";

            mensagem = mensagem.replaceAll(":recepcao:", recepcao);
            mensagem = mensagem.replaceAll(":nomeEstudante:", estudante.getPessoa().getPrimeiroNome());
            mensagem = mensagem.replaceAll(":endereco:", "Endereço");
            mensagem = mensagem.replaceAll(":numero:", "Número");
            mensagem = mensagem.replaceAll(":telefone:", "Telefone");

            EmailUtils.enviarHtmlEmail(recepcao + " à AARF", mensagem, emailDestinatario);

        } catch (EmailException e) {
            Messages.addWarn(null, "Ocorreu um erro ao enviar o e-mail");
            e.printStackTrace();
        }
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

}
