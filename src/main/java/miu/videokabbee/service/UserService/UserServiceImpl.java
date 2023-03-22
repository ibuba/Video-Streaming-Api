<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java
package miu.videokabbee.service.UserService;
=======
package miu.videokabbee.service.UserServiceImpl;


>>>>>>> ba75bc6b6734c38dda88ea4c40ab3229737c6800:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import miu.videokabbee.domain.Token;
import miu.videokabbee.config.security.JwtUtil;
import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.dto.UserDTO;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java
import miu.videokabbee.repository.RoleRepository;
=======
import miu.videokabbee.service.UserInterfaceService;
import miu.videokabbee.service.emailSender.EmailService;
import org.modelmapper.ModelMapper;
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java
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
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java

   TokenServiceInterface tokenServiceInterface;
=======
    TokenServiceInterface tokenServiceInterface;

>>>>>>> ba75bc6b6734c38dda88ea4c40ab3229737c6800:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java

    @Autowired
=======
   TokenServiceInterface tokenServiceInterface;
   @Autowired
   PasswordEncoder passwordEncoder;

   @Autowired
    EmailService emailService;
   @Autowired
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java

    AuthenticationManager authenticationManager;
   @Autowired
    private ModelMapper modelMapper;


    @Autowired
    RoleRepository roleRepository;


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
    public UserDTO getUserById(Long id) {
        var user =  userRepository.findById(id);
         return (user.isPresent())?
                modelMapper.map( user.get(),UserDTO.class): null;
    }
    @Override
<<<<<<< HEAD
    public List<Users> findAllUsers() {
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java
        return userRepository.findAll();
=======
    public String authenticate(String email, String password) {
        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));
            var user = ((UserDetails) user1.getPrincipal());
            return jwtUtil.generateToken(user);

        } catch (Exception e) {
            return "not authenticated";
        }
>>>>>>> new_branch
=======
       return userRepository.findAll();
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java
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
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserServiceImpl.java


=======
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2:src/main/java/miu/videokabbee/service/UserServiceImpl/UserServiceImpl.java
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
    /**
     * the local date is used for calcultating the time of the user to be locked after many attempts
     * the log in attempts counting and the restricted also for this method
     */
    private LocalDateTime startTime;
    private int loginAttempt = 0;
    private boolean restricted = false;


    @Override
        public int getLoginAttempt() {
            return loginAttempt;
        }


    @Override
        public void setLoginAttempt(int loginAttempt) {
            this.loginAttempt = loginAttempt;
        }

    @Override
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

    }

