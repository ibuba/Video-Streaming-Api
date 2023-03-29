
package miu.videokabbee.service.videoservice;
import miu.videokabbee.domain.Comment;
import miu.videokabbee.domain.Video;
import miu.videokabbee.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;


    @Override
    public void addVideo(Video video) {
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }


    @Override
    public Optional<Video>  getVideoWithUrl(String url) {
        return Optional.ofNullable(videoRepository.findByUrl(url).orElse(null));
    }

    @Override
    public Optional<Video> getVideoWithId(String id) {
        return Optional.ofNullable(videoRepository.findById(id).orElse(null));
    }

    @Override
    public Optional<Video> getVideoWithTitle(String title) {
        return Optional.ofNullable(videoRepository.findByTitle(title).orElse(null));
    }
    @Override
    public List<Comment> getCommentsByVideoId(String videoId) {
        return videoRepository.findCommentsByVideoId(videoId);
    }

    @Override
    public void incrementViewCount(String videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.incrementViewCount();
        videoRepository.save(video);
    }

    @Override
    public Comment addCommentToVideo(String videoId, Comment comment) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.addComment(comment);
        videoRepository.save(video);
        return videoRepository.saveComment(comment);
    }
    @Override
    public void incrementLikeCount(String videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.incrementLikeCount();
        videoRepository.save(video);
    }



}
