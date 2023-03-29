package miu.videokabbee.controller;

import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Comment;
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

    private final VideoService videoService;


    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVideos(){
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVideoWithId(@PathVariable("id") Long id) {
        var video =videoService.getVideoWithId(id);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
    @GetMapping("/{title}")
    public ResponseEntity<?> getVideoWithTitle(@PathVariable("title") String title) {
        var video= videoService.getVideoWithTitle(title).orElse(null);;
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping("/{url}")
    public ResponseEntity<?> getVideoWithUrl(@PathVariable("url") String url) {
        var  video= videoService.getVideoWithUrl(url).orElse(null);

    return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("Admin")
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
    public ResponseEntity<Comment> addCommentToVideo(@PathVariable Long videoId, @RequestBody Comment comment) {
        Comment savedComment = videoService.addCommentToVideo(videoId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
    @PostMapping("/{videoId}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Long videoId) {
        videoService.incrementViewCount(videoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{videoId}/like")
    public ResponseEntity<Void> incrementLikeCount(@PathVariable Long videoId) {
        videoService.incrementLikeCount(videoId);
        return ResponseEntity.noContent().build();
    }




}
