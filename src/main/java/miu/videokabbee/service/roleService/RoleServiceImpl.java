
package miu.videokabbee.service.roleService;

import miu.videokabbee.domain.Role;
import miu.videokabbee.repository.RoleRepository;
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

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> new Role(role.getId(), role.getName()))
                .collect(Collectors.toList());
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

//    @Override
//    public Optional<Role> getByRoleName(String userName) {
//        Optional<Role> optionalRole = roleRepository.findByName(userName);
//        if (optionalRole.isPresent()) {
//            optionalRole.get();
//            return optionalRole;
//
//        } else {
//
//            return null;
//        }
//    }

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
    public void deleterRole(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.deleteById(id);
        }

    }

}
