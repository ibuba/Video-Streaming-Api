package miu.videokabbee.repository;


import miu.videokabbee.domain.Role;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {


    Optional<Role> findById(String id);

    //
//    List<Role> findAll();
//
//    Role findByName(String user);
//
//    void deleteById(String id);
//
//    @Query("SELECT r FROM Role r WHERE r.email= ?1")
//    Role findByEmail(String email);
    Role findByName(String name);
}

