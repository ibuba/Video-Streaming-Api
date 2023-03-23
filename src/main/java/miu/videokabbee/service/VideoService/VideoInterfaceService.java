package miu.videokabbee.service.VideoService;


import miu.videokabbee.domain.Video;

import java.util.List;

public interface VideoInterfaceService {
    List<Video> getAllVideos();
    Video getVideo(Long id);
    Video createVideo(Video video);
    Video updateVideo(Long id,Video video);
    void deleteVideo(Long id);
}
