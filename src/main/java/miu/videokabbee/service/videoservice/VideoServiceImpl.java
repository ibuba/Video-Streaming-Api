package miu.videokabbee.service.videoservice;

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

    public List<Video> searchVideos(String word) {
        return   videoRepository.findByTitleContainingOrGenreContainingOrPopularityContaining(word,word,word)
                .orElse(null);
    }



}
