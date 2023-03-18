package miu.videokabbee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
import miu.videokabbee.service.tillo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.password.PasswordEncoder;
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


    // Getting All Users
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        var users = userInterfaceService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

  // Getting user By Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") Long id) {
        var user = userInterfaceService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ExceptionHandling("not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
 //Registering new Users
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

    //User loggingIn
    @GetMapping("/logIn")
    public String login() {
        return "LoggedIn";
    }

// User LoggingOut
    @GetMapping("/logout")

    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            userInterfaceService.logOut(request);
            return ResponseEntity.ok("logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not log out your account is still active");
        }


    }


    @Autowired
    private UserService userService;
 // User Password Reset
    @PostMapping("/reset/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email) {
        try {
            userService.resetPassword(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
// verifying User OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        try {
            userService.verifyOtp(email, otp, newPassword);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // Generating refreshToken
    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return userInterfaceService.refreshToken(refreshTokenRequest);
    }

// Updating Users profile
    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile( @RequestBody Users users){
        var user = userInterfaceService.updateUserProfile( users);
        if(user !=null)
        return new ResponseEntity<>(user,HttpStatus.OK);
        else
            return new ResponseEntity<>(new ExceptionHandling("user is not found"),HttpStatus.NOT_FOUND);

    }


}

