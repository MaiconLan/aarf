package utils.email;

import model.Anexo;
import model.Pessoa;
import org.apache.commons.mail.*;

import javax.validation.constraints.Null;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class Email implements Callable {

    protected final static String smtp = "smtp.gmail.com";
    protected final static String emailRemetente = "sistema.aarf@gmail.com";
    protected final static String emailSenha = "#e#j#m#m#r";
    protected final static boolean authentication = true;

    protected String titulo;
    protected String mensagem;
    protected String emailDestinatario;
    protected Anexo anexo;

    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public Email(String titulo, String mensagem, String emailDestinatario, Anexo anexo) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.emailDestinatario = emailDestinatario;
        this.anexo = anexo;
    }

    public Email(String titulo, String mensagem, String emailDestinatario) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.emailDestinatario = emailDestinatario;
    }

    protected abstract void enviarEmail() throws EmailException;

    public void enviar(){
        Future future = threadpool.submit(this);
    }

    @Override
    public Object call() throws Exception {
        enviarEmail();
        return null;
    }
}
