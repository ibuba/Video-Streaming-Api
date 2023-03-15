package miu.videokabbee.service.UserServiceImpl;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import java.util.List;
import java.util.Optional;

public interface roleinterfaceService {
    List<Role> getAllRole();
    Optional<Role> getByRollId(Long id);
   Optional<Role> getByRolename(String username);
    Role save(Role role);
    Optional<Role> updateRole(Role role,String roleId);
    void deleterRole(Long id);
}
