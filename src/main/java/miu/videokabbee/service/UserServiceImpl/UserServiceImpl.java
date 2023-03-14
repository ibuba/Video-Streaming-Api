package miu.videokabbee.service.UserServiceImpl;


import miu.videokabbee.config.security.JwtUtil;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.repository.UserRepository;
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

    UserRepository userRepository;
    @Autowired

    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

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
    public String authenticate(String email, String password) {
        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, passwordEncoder.encode(password)));
            var user = ((UserDetails) user1.getPrincipal());
            return jwtUtil.generateToken(user);

        } catch (Exception e) {
            return "not authenticated";
        }
    }

//    @Override
//    public void saveUserWithDefaultRole(Users users) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(users.getPassword());
//        users.setPassword(encodedPassword);
//        Role roleUser = roleRepository.findByName(users.getUserName());
//       users.getRoles();
//        userRepository.save(users);
//    }

    @Override
    public Users get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

}