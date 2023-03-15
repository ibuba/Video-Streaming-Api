package miu.videokabbee.service;


import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import org.springframework.security.core.userdetails.User;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);
    String authenticate(String  email, String password );
//    User Creat (User user);
    Users Creat(Users user);
}
