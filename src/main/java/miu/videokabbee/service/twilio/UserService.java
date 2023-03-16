package miu.videokabbee.service.twilio;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
 @Autowired
 private RoleRepository roleRepository;
    @Autowired
    private TwilioService twilioService;

    public void resetPassword(String email) throws Exception {
        Users user = userRepository.findByContactEmail(email).orElseThrow();
        System.out.println(user.toString());
        if (user == null) {
            throw new Exception("User not found with email " + email);
        }
        String otp = generateOtp();
        user.setOtp(otp);
        userRepository.save(user);

        String message = "Your OTP for password reset is " + otp;
        twilioService.sendSms(user.getContact().getPhone(), message);
        System.out.println(otp);
    }

    public void verifyOtp(String email, String otp, String newPassword) throws Exception {
        var user = userRepository.findByContactEmail(email).orElseThrow();
        if (user == null) {
            throw new Exception("User not found with email " + email);
        }

        if (!otp.equals(user.getOtp())) {
            throw new Exception("Invalid OTP");
        }

        user.setPassword(newPassword);
        user.setOtp(null);
        userRepository.save(user);
    }

    private String generateOtp() {
        // Generate a random 6-digit OTP
        return String.format("%06d", new Random().nextInt(999999));
    }

//    public  void assignRoleToUser(Role role, String userName) {
//        var user  = userRepository.findByContactEmail(userName).orElseThrow(
//                ()->new UsernameNotFoundException("not-found user")
//        );
//        var listOfRoles =user.getRole();
////        if (role != null && user != null) {
//            listOfRoles.add(role);
//            user.setRole(listOfRoles);
//            userRepository.save(user);
//        }
    public boolean hasRole(String roleName, String userName) {
//        Optional<Role> role = roleRepository.findByC(roleName);
        Users user = userRepository.findByUserName(userName);
        if (roleName != null && user != null) {
            return user.getRole().contains(roleName);
        }
        return false;
    }

}
