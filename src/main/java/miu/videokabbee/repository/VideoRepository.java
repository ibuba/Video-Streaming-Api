package miu.videokabbee.repository;

import miu.videokabbee.domain.Video;
import miu.videokabbee.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends MongoRepository<Video,String> {

    Optional<Video> findById(String videoId);
    Optional<Video> findByTitle(String title);
    Optional<Video> findByUrl(String url);
}
