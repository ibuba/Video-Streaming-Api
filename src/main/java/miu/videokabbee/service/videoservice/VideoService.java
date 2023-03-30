
package miu.videokabbee.service.videoservice;

import miu.videokabbee.domain.Comment;
import miu.videokabbee.domain.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    void addVideo(Video video);
    List<Video> getAllVideos();

    Optional<Video>  getVideoWithUrl(String url);
    Optional<Video> getVideoWithId(String id);
    Optional<Video>  getVideoWithTitle(String title);
//    Comment addCommentToVideo(String videoId, Comment comment) ;

    List<Comment> getCommentsByVideoId(String videoId);


    void incrementViewCount(String videoId);

    void incrementLikeCount(String videoId);

    // dfferent way to add coment
    void addCommentToVideo(Long userId, String videoId, String text);

}
