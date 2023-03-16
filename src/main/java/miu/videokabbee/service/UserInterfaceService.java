package miu.videokabbee.service;


import miu.videokabbee.domain.Users;
import miu.videokabbee.dto.LoginResponse;
import miu.videokabbee.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);

    ResponseEntity<?> authenticate(String  email, String password );
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    String updateUserProfile(Users users);
}
