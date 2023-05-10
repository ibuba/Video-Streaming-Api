package miu.videokabbee.repository;


import miu.videokabbee.domain.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription,Long> {

}
