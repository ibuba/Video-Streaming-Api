package miu.videokabbee.service.UserServiceImpl;



import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import miu.videokabbee.config.security.UserDetailCustom;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    }

