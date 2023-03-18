package miu.videokabbee.service.UserServiceImpl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import miu.videokabbee.domain.Token;
import miu.videokabbee.config.security.JwtUtil;
import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor

public class UserServiceImpl implements UserInterfaceService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenServiceInterface tokenServiceInterface;


    @Autowired

    AuthenticationManager authenticationManager;


    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public String register(Users users) {
        if (userRepository.existsByContact_Email(users.getContact().getEmail())) {
            return "Email-taken";
        } else if (userRepository.existsByUserName(users.getUserName())) {
            return "Username-taken";

        }
        userRepository.save(users);
        return "Success";
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }


    public void logOut(HttpServletRequest request) {
        String tokenName = "";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            tokenName = authorizationHeader.substring(7);
        }
        Token token = new Token();
        token.setTokenName(tokenName);
        tokenServiceInterface.create(token);
    }

    @Override
    // Generating access Token for User
    public ResponseEntity<?> authenticate(String email, String password) {

        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            var user = ((UserDetails) user1.getPrincipal());
            String refreshToken = jwtUtil.generateRefreshToken(email);
            String accessToken = jwtUtil.generateToken(user);
            LoginResponse l = new LoginResponse(accessToken, refreshToken);
            return new ResponseEntity<>(l, HttpStatus.OK);


        } catch (Exception e) {

            return new ResponseEntity<>("not authenticated", HttpStatus.OK);
        }


    }

    @Override
    // Generating refresh Token for User
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew in this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");

            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());

        }
        return new LoginResponse();
    }

    /**
     * the local date is used for calcultating the time of the user to be locked after many attempts
     * the log in attempts counting and the restricted also for this method
     */
    private LocalDateTime startTime;
    private int loginAttempt = 0;
    private boolean restricted = false;

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    @Override
    // Locking User after many Attempts
    public ResponseEntity<?> checkAttemptAndLock() {
        System.out.println(loginAttempt);
        if (!restricted) {
            this.restricted = true;
            startTime = LocalDateTime.now();
        }
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        if (duration.getSeconds() > 10) {
            loginAttempt = 0;
            restricted = false;
        }
        return new ResponseEntity<>("so many attempts try again later after 12 sec currentTime= " + duration.getSeconds(), HttpStatus.OK);

    }


    @Override
    public String updateUserProfile(Users users) {
        var user1 = userRepository.findByContactEmail(users.getContact().getEmail());
        if (user1.isPresent()) {
            var user = user1.get();
            user.setFirstName(users.getFirstName());
            user.setLastName(users.getUserName());
            user.setAge(users.getAge());
            user.setRole(users.getRole());
            user.setUserName(users.getUserName());
            user.setPassword(users.getPassword());
            user.setContact(users.getContact());
            user.setAddress(users.getAddress());
            user.setOtp(users.getOtp());
            userRepository.save(user);
            return "user Profile Updated Successfully";
        }
        return "user is not found";
    }
}

