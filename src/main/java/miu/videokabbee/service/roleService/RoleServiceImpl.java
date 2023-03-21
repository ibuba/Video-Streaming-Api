
package miu.videokabbee.service.roleService;

import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }


    @Override
    public Optional<Role> getRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            optionalRole.get();
            return optionalRole;

        } else {

            return null;
        }
    }

   @Override
   public Role saveRole(Role role) {
       return roleRepository.save(role);
   }

    @Override
    public Role updateRole(Role role, Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());

        if (optionalRole.isPresent()) {
            Role roles = optionalRole.get();
            roles.setName(role.getName());
            roles.setId(role.getId());
            roleRepository.save(role);
            return role;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRole(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.deleteById(id);
        }

    }

    @Override
    public void deleteRoleFromUser(Role role,Long id) {
        var user = userRepository.findById(id).orElseThrow(()->
                new UsernameNotFoundException("usr  not available"));

        if(user.getRole().equals(role)){
            user.getRole().remove(role.getId());
        }
        else {
            new Exception(role.getName()+ "has no role for this user="+ user.getFirstName());
        }
    }

   @Override
   public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    //checking if the user has role
    @Override
    public boolean hasRole(String roleName, String userName) {
//        Users user = userRepository.findByUserName(userName);
//        if (roleName != null && user != null) {
//            return user.getRole().contains(roleName);
//        }
     return false;
    }

    //Assignment role to a user

    @Override
    public void assignRoleToUser(Long roleId, String userName) {
        var user = userRepository.findByContactEmail(userName).orElseThrow(
                () -> new UsernameNotFoundException("not-found user")
        );
        var role = roleRepository.findById(roleId);
        var listOfRoles = user.getRole();
        if (role.get().getName() != null) {
            listOfRoles.add(role.get());
            user.setRole(listOfRoles);
            userRepository.save(user);
        }

    }


}



