package miu.videokabbee.controller;

import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Video;
import miu.videokabbee.service.videoservice.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getVideoWithId(@PathVariable("id") String id) {
        Video video = videoService.getVideoWithId(id).orElse(null);
        ;
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getVideoWithTitle(@PathVariable("title") String title) {
        Video video = videoService.getVideoWithTitle(title).orElse(null);
        ;
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping("/{url}")
    public ResponseEntity<?> getVideoWithUrl(@PathVariable("url") String url) {
        Video video = videoService.getVideoWithUrl(url).orElse(null);
        ;
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("Admin")
    public ResponseEntity<String> addVideo(@RequestBody Video video) {
        videoService.addVideo(video);
        return new ResponseEntity<>("Video created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchVideo(@RequestParam("movie") String word) {
        var searchedVideo = videoService.searchVideos(word);
        if (searchedVideo.isEmpty()) {
            return new ResponseEntity<>( "no video found with the keyword given", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(searchedVideo, HttpStatus.OK);
        }
    }

