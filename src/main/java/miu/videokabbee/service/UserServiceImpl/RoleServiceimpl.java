//package miu.videokabbee.service.UserServiceImpl;
//
//import miu.videokabbee.ExceptionHandling.ExceptionHandling;
//import miu.videokabbee.domain.Role;
//import miu.videokabbee.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RoleServiceimpl implements roleinterfaceService{
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Override
//    public List<Role> getAllRole() {
//        return roleRepository.findAll() ;
//    }
//
//    @Override
//    public Optional<Role> getByRollId(Long id) {
//        return roleRepository.findById(id);
//    }
//
//    @Override
//    public Optional<Role> getByRolename(String username) {
//        return roleRepository.findByName(username);
//    }
//
//    @Override
//    public Role save(Role role) {
//        return roleRepository.save(role);
//    }
//
////   @Override
////    public Optional<Role> updateRole(Role role,String id) {
////        Optional<Role> existingRole = roleRepository.findById(role.getId());
////
////        if (existingRole.isPresent()) {
////            existingRole.get().setName(role.getName());
////            existingRole.get().setName(role.getId());
////            return roleRepository.save(existingRole.get());
////        } else {
////          throw new ExceptionHandling("Role", "id", roleId);
////       }
////   }   ;
//
//        @Override
//    public void deleterRole(Long id) {
//     roleRepository.deleteById(id);
//
//    }
//}