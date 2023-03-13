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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserServiceImpl userInterfaceService;
    private final PasswordEncoder passwordEncoder;
    private final TokenServiceInterface tokenServiceInterface;

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