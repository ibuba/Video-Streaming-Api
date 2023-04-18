package miu.videokabbee.repository;

import miu.videokabbee.domain.PlayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends MongoRepository<PlayList,Long> {
}
