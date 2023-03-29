package miu.videokabbee.repository;

import miu.videokabbee.domain.Comment;
import miu.videokabbee.domain.Video;
import miu.videokabbee.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends MongoRepository<Video,Long> {

    Optional<Video> findById(Long videoId);
    Optional<Video> findByTitle(String title);
    Optional<Video> findByUrl(String url);

    List<Comment> findCommentsByVideoId(String videoId);
    Comment saveComment(Comment comment);
    void incrementViewCount(String videoId);
    void incrementLikeCount(String videoId);
}
