package miu.videokabbee.service;


import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;


public interface UserInterfaceService {
    String register(Users users);

    Users findById(Long id);

    String authenticate(String email, String password);

    // ----------------------------------
   // void saveUserWithDefaultRole(Users users);

    Users get(Long id);
    Role save(Role role);


}
