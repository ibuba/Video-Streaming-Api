package miu.videokabbee.repository;


import miu.videokabbee.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {
    Boolean existsByUserName(String userName);

    Boolean existsByContact_Email(String email);

    Optional<Users> findByContactEmail(String email);
}
