package miu.videokabbee.repository;

import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String userName);


}
