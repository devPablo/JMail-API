package org.bonilla.authentication;

import org.bonilla.reader.DataReader;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.File;
import java.util.Properties;

/**
 * Class that authenticates credentials for
 * a given e-mail & handles protocol configuration.
 *
 * @author  Pablo Bonilla G.
 * @version 1.0
 * @since   2018-11-22
 */

public class JMailManager {
    private String email;
    private String password;
    private String host;
    private int port;
    private boolean auth;
    private boolean tls;

    /**
     * Declares all the JMail data.
     *
     * @param file JSON file that contains the JMail data.
     */
    public JMailManager(File file) {
        DataReader dr = new DataReader(file);
        email = dr.getEmail();
        password = dr.getPassword();
        host = dr.getHost();
        port = Integer.parseInt(dr.getPort());
        auth = Boolean.parseBoolean(dr.getAuth());
        tls = Boolean.parseBoolean(dr.getTls());
    }

    /**
     * Declares all the JMail data.
     *
     * @param email E-Mail that will be authenticated.
     * @param password Password of the email provided.
     * @param host Host of the e-mail provider.
     * @param port Port of the e-mail provider.
     * @param auth Attempts to authenticate the e-mail.
     * @param tls Attempts to establish a TPS-protected connection.
     */
    public JMailManager(String email, String password, String host, int port, boolean auth, boolean tls) {
        this.email = email;
        this.password = password;
        this.host = host;
        this.port = port;
        this.auth = auth;
        this.tls = tls;
    }

    /**
     * Attempts to authenticate the given e-mail
     * and the password provided.
     *
     * @return JMail object for e-mail creation.
     */
    public JMail authMail() {
        Properties p = setProperties();
        Session session = Session.getInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        JMail jMail = new JMail(session, email);
        return jMail;
    }

    /**
     *
     * @return Properties containing the SMTP settings.
     */
    private Properties setProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", port);
        p.put("mail.smtp.auth", auth);
        p.put("mail.smtp.starttls.enable", tls);
        return p;
    }
}
