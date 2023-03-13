package miu.videokabbee.controller;


import lombok.RequiredArgsConstructor;
import miu.videokabbee.config.security.JwtUtil;
import miu.videokabbee.config.security.UserDetailCustom;
import miu.videokabbee.dto.LogInRequest;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserInterfaceService userDetailCustom;
    @PostMapping
    public ResponseEntity<?> authenticateToken(@RequestBody LogInRequest request) {
        var user = userDetailCustom.authenticate(request.getEmail(), request.getPassword());
        return user.equals("not authenticated")?
                new ResponseEntity<>(user, HttpStatus.NOT_FOUND):
                new ResponseEntity<>(user,HttpStatus.OK);
    }

}
