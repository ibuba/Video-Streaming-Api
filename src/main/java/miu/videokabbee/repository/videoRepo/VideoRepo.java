package miu.videokabbee.repository.videoRepo;

import miu.videokabbee.domain.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends MongoRepository<Video,Long> {

}
