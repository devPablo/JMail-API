import org.bonilla.authentication.JMail;
import org.bonilla.authentication.JMailManager;

import javax.mail.MessagingException;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        JMailManager jmm = new JMailManager(new File("src/JMailData.json"));
        JMail mail = jmm.authMail();

        mail.setTo("to-email@gmail.com");
        String message = "Hello <h4 style=margin:0;display:inline-block;>" + mail.getTo() + "</h4> </br>This is an email to test the JMail API.";

        try {
            mail.setFrom("my-email@gmail.com");
            mail.setSubject("JMail API for you");
            mail.setPersonal("JMail");
            mail.setText(message, "utf-8", "html");
            mail.send();
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}