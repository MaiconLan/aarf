package utils.email;

import model.Anexo;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;

public class EmailAnexo extends Email {

    public EmailAnexo(String titulo, String mensagem, String emailDestinatario, Anexo anexo) {
        super(titulo, mensagem, emailDestinatario, anexo);
    }

    @Override
    protected void enviarEmail() throws EmailException {
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