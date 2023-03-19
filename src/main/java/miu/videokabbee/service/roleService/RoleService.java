package miu.videokabbee.service.roleService;

import miu.videokabbee.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Role saveRole(Role role);

    Role updateRole(Role role, Long roleId);

    void deleteRole(Long id);
    void deleteRoleFromUser(Role role,Long id);

    Role createRole(Role role);

    boolean hasRole(String roleName, String userName);
    public void assignRoleToUser(Long  roleId, String userName);

}
