package miu.videokabbee.service;


import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import miu.videokabbee.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterfaceService {
    String register(Users users);
    UserDTO getUserById(Long id);
    List<Users> findAllUsers();
    ResponseEntity<?> authenticate(String  email, String password );
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    String updateUserProfile(Users users);


    public  void resetPassword(String email, String password);
    ResponseEntity<?> checkAttemptAndLock();
     int getLoginAttempt();
     void setLoginAttempt(int logInattempts);


}
