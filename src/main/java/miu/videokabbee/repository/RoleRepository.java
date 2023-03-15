package miu.videokabbee.repository;

import miu.videokabbee.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,Long>{
    Optional<Role> findByName(String name);

    @Override
    Optional<Role> findById(Long id);
}
