package miu.videokabbee.service.UserService;

import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserInterfaceService.java

    List<Users> findAllUsers();

=======
    List<Users> findAllUsers();
>>>>>>> ba75bc6b6734c38dda88ea4c40ab3229737c6800:src/main/java/miu/videokabbee/service/UserInterfaceService.java
    ResponseEntity<?> authenticate(String  email, String password );
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    String updateUserProfile(Users users);
    ResponseEntity<?> checkAttemptAndLock();
<<<<<<< HEAD:src/main/java/miu/videokabbee/service/UserService/UserInterfaceService.java

    int getLoginAttempt();
    void setLoginAttempt(int logInattempts);
=======
     int getLoginAttempt();
     void setLoginAttempt(int logInattempts);


>>>>>>> ba75bc6b6734c38dda88ea4c40ab3229737c6800:src/main/java/miu/videokabbee/service/UserInterfaceService.java
}


