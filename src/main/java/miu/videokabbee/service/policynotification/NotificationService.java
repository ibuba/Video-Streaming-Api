package miu.videokabbee.service.policynotification;

import jakarta.mail.MessagingException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * NotificationService interface with a sendNotification method to define the contract for sending notifications:
 */
public interface NotificationService {




    void sendNotification(String to, String subject, String notification, Date date) throws MessagingException;//notifiation is messagee

   // void sendNotification();
    //List<Users> findAll();
}
