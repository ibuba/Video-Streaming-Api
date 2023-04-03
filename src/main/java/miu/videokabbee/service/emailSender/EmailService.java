package miu.videokabbee.service.emailSender;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import miu.videokabbee.domain.PolicyUpdateNotification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {


    private final String senderEmail = "ibesto682@gmail.com";
    private final String senderPassword ="ebopwcrleyjciska";
    private final String emailHost = "smtp.gmail.com";
    private final int emailPort = 587;

    public void sendVerificationEmail(String recipientEmail,
                                      String verificationLink)
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailHost);
        props.put("mail.smtp.port", emailPort);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Password reset verification link");
        message.setText("Please click on the link below to reset your password:\n" + verificationLink);

        Transport.send(message);
    }


}
