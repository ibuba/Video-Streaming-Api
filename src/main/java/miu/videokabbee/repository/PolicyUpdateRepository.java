package miu.videokabbee.repository;

import miu.videokabbee.domain.PolicyEmailMessage;
import miu.videokabbee.domain.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PolicyUpdateRepository extends ReactiveMongoRepository<PolicyEmailMessage,Long> {

    Optional<Users> findByContactPhone(String phone);
}
