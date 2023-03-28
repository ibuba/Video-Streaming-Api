package miu.videokabbee.service.videoservice;

import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Video;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    void addVideo(Video video);
    List<Video> getAllVideos();

    Optional<Video>  getVideoWithUrl(String url);
    Optional<Video> getVideoWithId(String id);
    Optional<Video>  getVideoWithTitle(String title);

    List<Video> searchVideos(String word);

}
