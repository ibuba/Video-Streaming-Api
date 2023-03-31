package miu.videokabbee.service.emailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import miu.videokabbee.domain.PolicyUpdateNotification;
import miu.videokabbee.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailPolicyNotification implements NotificationService {

    private final String senderEmail = "ibesto682@gmail.com";
    private final String senderPassword = "ebopwcrleyjciska";
    private final String emailHost = "smtp.gmail.com";
    private final int emailPort = 587;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNotification(String recipientEmail, PolicyUpdateNotification notification) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailHost);
        props.put("mail.smtp.port", emailPort);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
        helper.setTo(recipientEmail);
        helper.setSubject("Policy Update Notification");
        helper.setText(notification.getMessage(), true);
        javaMailSender.send(message);
    }
}
//send update notification
