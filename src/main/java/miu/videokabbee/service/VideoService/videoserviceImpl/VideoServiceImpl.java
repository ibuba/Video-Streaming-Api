package miu.videokabbee.service.VideoService.videoserviceImpl;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.Video;
import miu.videokabbee.repository.videoRepo.VideoRepo;
import miu.videokabbee.service.VideoService.VideoInterfaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoInterfaceService {

    private final VideoRepo videoRepo;

    @Override
    public List<Video> getAllVideos() {
        return videoRepo.findAll();
    }

    @Override
    public Video getVideo(Long id) {
        return videoRepo.findById(id).orElse(null);
    }

    @Override
    public Video createVideo(Video video) {
      return   videoRepo.save(video);

    }

    @Override
    public Video updateVideo(Long id, Video video) {
        var video1 = videoRepo.findById(id).get();
        video1.setVideoDescription(video.getVideoDescription());
        video1.setCoverImage(video.getCoverImage());
        video1.setLanguage(video.getLanguage());
        video1.setTitle(video.getTitle());
       return videoRepo.save(video1);

    }

    @Override
    public void deleteVideo(Long id) {
        videoRepo.deleteById(id);

    }
}
