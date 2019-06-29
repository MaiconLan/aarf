package utils.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailHtml extends Email {

    public EmailHtml(String titulo, String mensagem, String emailDestinatario) {
        super(titulo, mensagem, emailDestinatario);
    }

    @Override
    protected void enviarEmail() throws EmailException {
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
}
