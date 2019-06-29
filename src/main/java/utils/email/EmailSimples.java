package utils.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSimples extends Email {

    public EmailSimples(String titulo, String mensagem, String emailDestinatario) {
        super(titulo, mensagem, emailDestinatario);
    }

    @Override
    protected void enviarEmail() throws EmailException {
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
}
