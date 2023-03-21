package miu.videokabbee.service.UserServiceImpl;



import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import miu.videokabbee.config.security.UserDetailCustom;
import miu.videokabbee.domain.Token;

import miu.videokabbee.config.security.JwtUtil;

import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.service.UserInterfaceService;
import miu.videokabbee.service.emailSender.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public String register(Users users) {
        if(userRepository.existsByContact_Email(users.getContact().getEmail())){
           return "Email-taken";
        }else
        userRepository.save(users);
        return users.getUserName()+"Successfully registered ";
    }

    public Users getUserById(Long id) {
        var user =  userRepository.findById(id);
         return (user.isPresent())?
                 user.get(): null;
    }



    public void logOut(HttpServletRequest request){
        String tokenName="";
        String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader!=null&& authorizationHeader.startsWith("Bearer ")) {
            tokenName= authorizationHeader.substring(7);
        }
        Token token =new Token();
        token.setTokenName(tokenName);
        tokenServiceInterface.create(token);
    }

    @Override
    public ResponseEntity<?> authenticate(String email, String password) {

        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            var user = ((UserDetails) user1.getPrincipal());
            String refreshToken= jwtUtil.generateRefreshToken(email);
            String accessToken=jwtUtil.generateToken(user);
            LoginResponse l=new LoginResponse(accessToken,refreshToken);
            return new ResponseEntity<>(l, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>("not authenticated", HttpStatus.OK);
        }

    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if(isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew in this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");

            final String accessToken = jwtUtil.doGenerateToken(  jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
           return   new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());

        }
        return new LoginResponse();
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
            var user=user1.get();
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

