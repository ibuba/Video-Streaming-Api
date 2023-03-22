package miu.videokabbee.repository;


import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {
    Boolean existsByContact_Email(String email);
    Optional<Users> findByContactEmail(String email);

<<<<<<< HEAD
    Users findByUserName(String userName);





=======
    @Override
    Optional<Users> findById(Long id);
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2
}
