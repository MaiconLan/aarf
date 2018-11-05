package utils;

import model.Anexo;
import model.Pessoa;
import org.apache.commons.mail.*;

import javax.validation.constraints.Null;
import java.io.File;

public abstract class EmailUtils {

    private final static String smtp = "smtp.gmail.com";
    private final static String emailRemetente = "sistema.aarf@gmail.com";
    private final static String emailSenha = "#e#j#m#m#r";
    private final static boolean authentication = true;

    public static void enviarEmailSimples(String titulo, String mensagem, String emailDestinatario) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(smtp);
        email.setAuthentication(emailRemetente, emailSenha);
        email.setSSL(authentication);
        email.addTo(emailDestinatario);
        email.setSmtpPort(587);
        email.setFrom(emailRemetente);
        email.setSubject(titulo);
        email.setMsg(mensagem);
        email.send();
    }

    public static void enviarHtmlEmail(String titulo, String mensagem, String emailDestinatario) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(smtp);
        email.setAuthentication(emailRemetente, emailSenha);
        email.setSSL(authentication);
        email.addTo(emailDestinatario);
        email.setSmtpPort(587);
        email.setFrom(emailRemetente);
        email.setSubject(titulo);
        email.setMsg(mensagem);
        email.send();
    }

    public static void enviarEmailAnexo(String titulo, String mensagem, String emailDestinatario, Anexo anexo) throws EmailException {
        File file = new File(anexo.getCaminho());

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(file.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(anexo.getNome());
        attachment.setName(file.getName());

        MultiPartEmail email = new MultiPartEmail();
        email.attach(attachment);
        email.setHostName(smtp);
        email.setAuthentication(emailRemetente, emailSenha);
        email.setSSL(authentication);
        email.addTo(emailDestinatario);
        email.setSmtpPort(587);
        email.setFrom(emailRemetente);
        email.setSubject(titulo);
        email.setMsg(mensagem);
        email.send();
    }

}
