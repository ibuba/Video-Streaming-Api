package miu.videokabbee.service.videoservice;

import miu.videokabbee.domain.Comment;
import miu.videokabbee.domain.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    void addVideo(Video video);
    List<Video> getAllVideos();

    Optional<Video>  getVideoWithUrl(String url);
    Optional<Video> getVideoWithId(Long videoId);
    Optional<Video>  getVideoWithTitle(String title);
    Comment addCommentToVideo(Long videoId, Comment comment) ;

    List<Comment> getCommentsByVideoId(String videoId);

    void incrementViewCount(Long videoId);

    void incrementLikeCount(Long videoId);
}
