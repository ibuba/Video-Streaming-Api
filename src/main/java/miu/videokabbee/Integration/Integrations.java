package miu.videokabbee.Integration;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.emailSender.EmailService;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class Integrations implements IntegrationInterface {

     private final EmailService emailService;
        private final UserRepository userRepository;
// Sending messages to user
    public void sendSuspiciousNotification(String email){
        var user=userRepository.findByContactEmail(email);
        if(user.isPresent()){
        String message = "hi " + " " + user.get().getFirstName()
                + " we saw suspicious activity on your account confirm if that s you";
        try {
            emailService.sendVerificationEmail(email, message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

            }
    }
}
