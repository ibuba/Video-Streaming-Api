package miu.videokabbee.controller;


import lombok.RequiredArgsConstructor;
import miu.videokabbee.dto.LogInRequest;
import miu.videokabbee.service.UserService.UserInterfaceService;
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
    private int loginAttempt =0;
    private boolean restricted=false;
    private LocalDateTime startTime;

    @PostMapping
    public ResponseEntity<?> authenticateToken(@RequestBody LogInRequest request) {

        if(loginAttempt ==5){
            System.out.println(loginAttempt);
            if(!restricted) {
                restricted=true;
                startTime=LocalDateTime.now();
            }
            Duration duration = Duration.between(startTime, LocalDateTime.now());
            if(duration.getSeconds()>10){
                doTask();
            }
           return new ResponseEntity<>("so many attempts try again later after 12 sec currentTime= "+ duration.getSeconds(),HttpStatus.OK);
            }


        var user = userDetailCustom.authenticate(request.getEmail(), request.getPassword());
        System.out.println(user.getBody());
        if(Objects.equals(user.getBody(), "not authenticated")){
            loginAttempt++;
        }

        return user.getBody().equals("not authenticated")?
                new ResponseEntity<>(user.getBody(), HttpStatus.NOT_FOUND):
                new ResponseEntity<>(user.getBody(),HttpStatus.OK);
    }



    public void doTask() {
        loginAttempt=0;
        restricted=false;
    }
}


