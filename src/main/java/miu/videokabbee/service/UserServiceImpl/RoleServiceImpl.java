package miu.videokabbee.service.UserServiceImpl;


import miu.videokabbee.domain.Role;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        Role roleCreated = new Role();
        roleCreated.setName(role.getName());
        roleCreated.setDescription(role.getDescription());
        Role savedRole = roleRepository.save(roleCreated);
        role.setId(savedRole.getId());
        return role;
    }

    @Override
    public Role updateRole(String id, Role role) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role roles = optionalRole.get();
            roles.setName(role.getName());
            roles.setDescription(role.getDescription());
            Role updatedRole = roleRepository.save(role);
            roles.setId(updatedRole.getId());
            return roles;
        } else {
            return null;
        }
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

//    @Override
//    public Role addRole(Role role) {
//        return roleRepository.insert(role);
//    }

    @Override
    public void deleteRole(String id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.deleteById(id);
        }

    }

    @Override
    public Role getRoleById(String id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            return role;

        } else {


            return null;
        }
    }


    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles; //roles.stream()
        // .map(role -> new Role(role.getId(), role.getName(), role.getDescription()))
        //  .collect(Collectors.toList());
    }
}


