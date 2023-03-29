
package miu.videokabbee.controller;

import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Comment;
import miu.videokabbee.domain.Video;
import miu.videokabbee.service.videoservice.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping( videoList)
    public ResponseEntity<?> getAllVideos(){
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
    @GetMapping(videoId)
    public ResponseEntity<?> getVideoWithId(@PathVariable("id") String id) {
        Video video=videoService.getVideoWithId(id).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +id)+" found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping(videoTitle)
    public ResponseEntity<?> getVideoWithTitle(@PathVariable("title") String title) {
        var video= videoService.getVideoWithTitle(title).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +title)+" found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping(videoUrl)
    public ResponseEntity<?> getVideoWithUrl(@PathVariable("url") String url) {
        Video video= videoService.getVideoWithUrl(url).orElse(null);
        if(video == null){
            return new ResponseEntity<>(new ExceptionHandling("no Videos With " +url)+" found",HttpStatus.NOT_FOUND);
        }
    return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PostMapping(newVideo)
  //  @PreAuthorize("Admin")
    public ResponseEntity<String> addVideo(@RequestBody Video video) {
        videoService.addVideo(video);
        return new ResponseEntity<>("Video created successfully.", HttpStatus.CREATED);
    }
    @GetMapping("/{videoId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByVideoId(@PathVariable String videoId) {
        List<Comment> comments = videoService.getCommentsByVideoId(videoId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{videoId}/comments")
    public ResponseEntity<Comment> addCommentToVideo(@PathVariable String videoId, @RequestBody Comment comment) {
        Comment savedComment = videoService.addCommentToVideo(videoId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
    @PostMapping("/{videoId}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable String videoId) {
        videoService.incrementViewCount(videoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{videoId}/like")
    public ResponseEntity<Void> incrementLikeCount(@PathVariable String videoId) {
        videoService.incrementLikeCount(videoId);
        return ResponseEntity.noContent().build();
    }

}
