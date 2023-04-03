package miu.videokabbee.service.policynotification;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;


@Service
public class NotificationUpdateService implements NotificationService {

    private JavaMailSender javaMailSender;


    public NotificationUpdateService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendNotification(String to, String subject, String notification, Date date) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");//our sender email
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(notification);
        simpleMailMessage.setSentDate(date);
        this.javaMailSender.send(simpleMailMessage);
    }


}


