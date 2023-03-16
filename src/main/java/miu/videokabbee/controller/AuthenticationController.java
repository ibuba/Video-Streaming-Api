package miu.videokabbee.controller;


import lombok.RequiredArgsConstructor;
import miu.videokabbee.Integration.IntegrationInterface;
import miu.videokabbee.Integration.Integrations;
import miu.videokabbee.dto.LogInRequest;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserInterfaceService userDetailCustom;
    private final IntegrationInterface integrations;
    @PostMapping
    public ResponseEntity<?> authenticateToken(@RequestBody LogInRequest request) {

        if(userDetailCustom.getLoginAttempt() ==5){
          return  userDetailCustom.checkAttemptAndLock();
        }

        var user = userDetailCustom.authenticate(request.getEmail(), request.getPassword());
        if(Objects.equals(user.getBody(), "not authenticated")){
            userDetailCustom.setLoginAttempt(userDetailCustom.getLoginAttempt()+1);
            if(userDetailCustom.getLoginAttempt()==3){
                integrations.sendSuspiciousNotification(request.getEmail());
            }
        }

        return user.getBody().equals("not authenticated")?
                new ResponseEntity<>(user.getBody(), HttpStatus.NOT_FOUND):
                new ResponseEntity<>(user.getBody(),HttpStatus.OK);
    }
}


