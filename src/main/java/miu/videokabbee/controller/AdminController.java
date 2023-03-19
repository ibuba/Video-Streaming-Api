package miu.videokabbee.controller;

import miu.videokabbee.domain.Role;
import miu.videokabbee.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            return new ResponseEntity<>(role.get(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @PutMapping("/role/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignRoleToUser(
            @RequestParam Long  roleId, @RequestParam String
            username) {
        roleService.assignRoleToUser(roleId,username);
         return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/roles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(role, id);
        if (updatedRole == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/role/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

}
