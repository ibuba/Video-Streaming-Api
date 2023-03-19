package miu.videokabbee.service.UserService;


import miu.videokabbee.domain.Users;

public interface UserInterfaceService {
    String register(Users users);

    Users findById(Long id);

    String authenticate(String email, String password);


}