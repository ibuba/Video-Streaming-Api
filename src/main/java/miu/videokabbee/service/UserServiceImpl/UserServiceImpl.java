package miu.videokabbee.service.UserServiceImpl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import miu.videokabbee.domain.Token;
import miu.videokabbee.config.security.JwtUtil;
import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.dto.UserDto;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.service.UserInterfaceService;
import miu.videokabbee.service.emailSender.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
   PasswordEncoder passwordEncoder;

   @Autowired
    EmailService emailService;

   @Autowired

    AuthenticationManager authenticationManager;
   @Autowired
    private ModelMapper modelMapper;

    @Autowired

   private  RoleRepository roleRepository;



    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    public String register(Users users) {
        if(userRepository.existsByContact_Email(users.getContact().getEmail())){
           return "Email-taken";
        }else
        userRepository.save(users);
        return users.getFirstName()+"Successfully registered ";
    }
    public UserDto getUserById(Long id) {
        var user =  userRepository.findById(id);
         return (user.isPresent())?
                modelMapper.map( user.get(),UserDto.class): null;
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




    // resting  password
    @Override
    public void resetPassword(String email,String password ) {
        var user=   userRepository.findByContactEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "user-notFound to be  Rest password"));
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
    }




    @Override
    public String updateUserProfile(Users users) {
        var user1 = userRepository.
                findByContactEmail(users.getContact().getEmail());
        if (user1.isPresent()) {
            var user = user1.get();
            user.setFirstName(users.getFirstName());
            user.setLastName(users.getLastName());
            user.setAge(users.getAge());
            user.setPassword(users.getPassword());
            user.getContact().setPhone(users.getContact().getPhone());
            user.setAddress(users.getAddress());
            userRepository.save(user);
            return "user Profile Updated Successfully";
        }
        return "user is not found";
    }
}

