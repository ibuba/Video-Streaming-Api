package miu.videokabbee.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Users;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
//import miu.videokabbee.service.tillo.TwilioOTPHandler;
import miu.videokabbee.service.tillo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

//    @Autowired
//    private TwilioOTPHandler handler;


    private final UserServiceImpl userInterfaceService;
   private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") Long id ){
        var user=userInterfaceService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ExceptionHandling("not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users users){
        String encodedPassword=passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
     var userRegistered =   userInterfaceService.register(users);

        if (userRegistered.equals("Username-taken") ||userRegistered.equals("Email-taken")  ) {
            return new ResponseEntity<>(new ExceptionHandling(userRegistered), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRegistered,HttpStatus.OK);
    }



    @Autowired
    private UserService userService;

    @PostMapping("/reset/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email) {
        try {
            userService.resetPassword(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        try {
            userService.verifyOtp(email, otp, newPassword);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
