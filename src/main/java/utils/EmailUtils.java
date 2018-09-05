package utils;

import model.Anexo;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;

public abstract class EmailUtils {

    private final static String smtp = "smtp.gmail.com";
    private final static boolean authentication = true;

    public static void enviarEmailSimples(String titulo, String mensagem, String emailDestinatario, String emailRemetente, String senha) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(smtp);
        email.setAuthentication(emailRemetente, senha);
        email.setSSL(authentication);
        email.addTo(emailDestinatario);
        email.setSmtpPort(587);
        email.setFrom(emailRemetente);
        email.setSubject(titulo);
        email.setMsg(mensagem);
        email.send();
    }

    public static void enviarEmailAnexo(String titulo, String mensagem, String emailDestinatario, String emailRemetente, String senha, Anexo anexo) throws EmailException {
        File file = new File(anexo.getCaminho());

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(file.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(anexo.getNome());
        attachment.setName(file.getName());

        MultiPartEmail email = new MultiPartEmail();
        email.attach(attachment);
        email.setHostName(smtp);
        email.setAuthentication(emailRemetente, senha);
        email.setSSL(authentication);
        email.addTo(emailDestinatario);
        email.setSmtpPort(587);
        email.setFrom(emailRemetente);
        email.setSubject(titulo);
        email.setMsg(mensagem);
        email.send();
    }

}
