package miu.videokabbee.service.UserServiceImpl;

import miu.videokabbee.domain.Role;


import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Role updateRole(String id, Role role);

    Role saveRole(Role role);

    // Role addRole(Role role);
    void deleteRole(String id);

    Role getRoleById(String id);

    List<Role> getAllRoles();

}

