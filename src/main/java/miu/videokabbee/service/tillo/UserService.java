package miu.videokabbee.service.tillo;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.emailSender.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private EmailService emailService;

    public void resetPasswordBySms(String email) throws Exception {
        Users user = userRepository.findByContactEmail(email).orElseThrow();

        System.out.println(user.getFirstName());

//        if (user == null) {
//            throw new Exception("User not found with email " + email);
//        }
        String otp = generateOtp();
        System.out.println(otp);
        user.setOtp(otp);
        userRepository.save(user);

        String message = "Your OTP for password reset is " + otp;

        twilioService.sendSms(user.getContact().getPhone(),message);

    }
    // Resting By Email
    public void   resetPasswordByEmail(String email) throws Exception {
        var user1 = userRepository.findByContactEmail(email).orElseThrow(()->
                new Exception("User not found with email " + email));
        String otp = generateOtp();
        System.out.println(otp);
        user1.setOtp(otp);
        userRepository.save(user1);
        String message = "Your OTP for password reset is " + otp;
        emailService.sendVerificationEmail(email, message);
    }
    public void verifyOtp(
            String email,
            String otp
            ) throws Exception {
        System.out.println("verify called");
        var user = userRepository.findByContactEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("user-not found")
        );

        if (!otp.equals(user.getOtp())) {
            throw new Exception("Invalid OTP");
        }
        System.out.println("previous password was"
                + user.getPassword());

        user.setOtp(null);
        userRepository.save(user);
    }

    private String generateOtp() {
        // Generate a random 6-digit OTP
        return String.format("%06d", new Random().nextInt(999999));
    }
}
