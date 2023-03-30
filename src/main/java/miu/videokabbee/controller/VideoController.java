package miu.videokabbee.controller;

import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.controller.router.VideoRouter;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Video;
import miu.videokabbee.service.videoservice.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static miu.videokabbee.controller.router.VideoRouter.*;


@RestController
@RequestMapping("/videos")

public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
  // get AllVideos 
    @GetMapping( videoList)
    public ResponseEntity<?> getAllVideos(){
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
    
    // get video By Id
    
    @GetMapping(videoId)
    public ResponseEntity<?> getVideoWithId(@PathVariable("id") String id) {
        Video video=videoService.getVideoWithId(id).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +id)+" found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(video, HttpStatus.OK);
    }


// getting a video By Title

    @GetMapping(videoTitle)
    public ResponseEntity<?> getVideoWithTitle(@PathVariable("title") String title) {
        var video= videoService.getVideoWithTitle(title).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +title)+" found",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(video, HttpStatus.OK);
    }
    
// getting a video By URL

    @GetMapping(videoUrl)
    public ResponseEntity<?> getVideoWithUrl(@PathVariable("url") String url) {

        Video video= videoService.getVideoWithUrl(url).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +url)+" found",HttpStatus.NOT_FOUND);
        }
    return new ResponseEntity<>(video, HttpStatus.OK);
    }


// adding a new video

    @PostMapping(newVideo)
  //  @PreAuthorize("Admin")
    public ResponseEntity<String> addVideo(@RequestBody Video video) {
        videoService.addVideo(video);
        return new ResponseEntity<>("Video created successfully.", HttpStatus.CREATED);
    }
// Searing a video by movie name 
    @GetMapping("/search")
    public ResponseEntity<?> searchVideo(@RequestParam("movie") String word) {
        var searchedVideo = videoService.searchVideos(word);
        if (searchedVideo.isEmpty()) {
            return new ResponseEntity<>( "no video found with the keyword given", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(searchedVideo, HttpStatus.OK);
        }
    }

