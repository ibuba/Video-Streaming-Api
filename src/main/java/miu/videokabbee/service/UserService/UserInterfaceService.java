package miu.videokabbee.service.UserService;

import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);

    List<Users> findAllUsers();

    ResponseEntity<?> authenticate(String  email, String password );
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    String updateUserProfile(Users users);
    ResponseEntity<?> checkAttemptAndLock();

    int getLoginAttempt();
    void setLoginAttempt(int logInattempts);
}


