package miu.videokabbee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Token;
import miu.videokabbee.domain.Users;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.service.UserServiceImpl.TokenService;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
//import miu.videokabbee.service.tillo.TwilioOTPHandler;
import miu.videokabbee.service.tillo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

<<<<<<< HEAD
    private final UserServiceImpl userInterfaceService;
    private final PasswordEncoder passwordEncoder;
    private final TokenServiceInterface tokenServiceInterface;
=======
//    @Autowired
//    private TwilioOTPHandler handler;


    private final UserServiceImpl userInterfaceService;
   private final PasswordEncoder passwordEncoder;
>>>>>>> 44a66e39ba1904b570535eec366e34120e60a47b

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") Long id) {
        var user = userInterfaceService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ExceptionHandling("not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users users) {
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        var userRegistered = userInterfaceService.register(users);

        if (userRegistered.equals("Username-taken") || userRegistered.equals("Email-taken")) {
            return new ResponseEntity<>(new ExceptionHandling(userRegistered), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRegistered, HttpStatus.OK);
    }

<<<<<<< HEAD
    @GetMapping("/logIn")
    public String login() {
        return "LoggedIn";
    }


    @GetMapping("/logout")

    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            userInterfaceService.logOut(request);
            return ResponseEntity.ok("logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not log out your account is still active");
        }

    }


}
=======


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
>>>>>>> 44a66e39ba1904b570535eec366e34120e60a47b
