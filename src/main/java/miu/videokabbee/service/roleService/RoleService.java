package miu.videokabbee.service.roleService;

import miu.videokabbee.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

//    Optional<Role> getByRoleName(String useName);

    Role saveRole(Role role);

    Role updateRole(Role role, Long roleId);

    void deleterRole(Long id);

}
