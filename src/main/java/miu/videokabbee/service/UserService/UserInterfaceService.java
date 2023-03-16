package miu.videokabbee.service.UserService;


import miu.videokabbee.domain.Users;

public interface UserInterfaceService {
    String register(Users users);

    Users findById(Long id);

    String authenticate(String email, String password);
//    Users createUser(Users user);

//    //Role interface
//    List<Role> getAllRole();
//    Optional<Role> getByRoleById(Long id);
//    Optional<Role> getByRoleName(String username);
//    Role saveRole(Role role);
//    Optional<Role> updateRole(Role role,String roleId);
//    void deleterRole(Long id);
//}
}