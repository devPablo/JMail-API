package org.bonilla.authentication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Class for e-mail creation.
 *
 * @author  Pablo Bonilla G.
 * @version 1.0
 * @since   2018-11-22
 */

public class JMail extends MimeMessage  {
    private String email;
    private String to;

    /**
     *
     * @param session Session authenticated
     * @param email
     * @see {@link JMailManager#authMail()}
     */
    public JMail(Session session, String email) {
        super(session);
        this.email = email;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setPersonal(String personal) throws MessagingException, UnsupportedEncodingException {
        InternetAddress internetAddress;
        internetAddress = new InternetAddress(email);
        internetAddress.setPersonal(personal);
        this.setFrom(internetAddress);
    }

    private void setRecipients() throws MessagingException {
        super.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo()));
    }

    public void send() throws MessagingException {
        setRecipients();
        try {
            Transport.send(this);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
