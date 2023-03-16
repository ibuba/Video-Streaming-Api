package miu.videokabbee.Integration;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor

public class Integrations implements IntegrationInterface {


        private final UserRepository userRepository;

    public void sendSuspiciousNotification(String email){
        var user=userRepository.findByContactEmail(email);

        user.ifPresent(users -> System.out.println("hi " + " " + users.getUserName()
                + " we saw suspicious activity on your account confirm if thats you"));

    }
}
