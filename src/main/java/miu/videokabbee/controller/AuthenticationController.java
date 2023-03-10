package miu.videokabbee.controller;


import lombok.RequiredArgsConstructor;
import miu.videokabbee.config.JwtUtil;
import miu.videokabbee.config.UserDetailCustom;
import miu.videokabbee.dto.LogInRequest;
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
    
    private final AuthenticationManager authenticationManager;
    private final UserDetailCustom userDetailCustom;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LogInRequest request ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
 final UserDetails user=userDetailCustom.loadUserByUsername(request.getEmail());
 if(user !=null){
      return ResponseEntity.ok(jwtUtil.generateToken(user));
 }
 return ResponseEntity.status(400).body("some error has occurred");
    }

}
