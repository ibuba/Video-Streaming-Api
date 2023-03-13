package miu.videokabbee.service.UserServiceImpl;


<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
import miu.videokabbee.domain.Token;
=======
import miu.videokabbee.config.security.JwtUtil;
>>>>>>> 44a66e39ba1904b570535eec366e34120e60a47b
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserInterfaceService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
<<<<<<< HEAD
  UserRepository userRepository;
    @Autowired
   TokenServiceInterface tokenServiceInterface;
=======
>>>>>>> 44a66e39ba1904b570535eec366e34120e60a47b

    UserRepository userRepository;
    @Autowired

    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

<<<<<<< HEAD
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
=======
    @Override
    public String authenticate(String email, String password) {
        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, passwordEncoder.encode(password)));
            var user = ((UserDetails) user1.getPrincipal());
            return jwtUtil.generateToken(user);
>>>>>>> 44a66e39ba1904b570535eec366e34120e60a47b

        } catch (Exception e) {
            return "not authenticated";
        }
    }

}