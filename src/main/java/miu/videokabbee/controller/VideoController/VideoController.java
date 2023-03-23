package miu.videokabbee.controller.VideoController;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.domain.Video;
import miu.videokabbee.repository.videoRepo.VideoRepo;
import miu.videokabbee.service.VideoService.VideoInterfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoInterfaceService videoService;

    // getting all videos
    @GetMapping
    public ResponseEntity<?> displayAllVideos() {
        var videos = videoService.getAllVideos();
        return (videos.size() > 1) ?
                new ResponseEntity<>(videos, HttpStatus.OK) : new ResponseEntity<>(
                new ExceptionHandling(
                        "Videos are  not available"),
                HttpStatus.NOT_FOUND);
    }

    // get video by id
    @GetMapping("/{id}")
    public ResponseEntity<?> displayVideoById(@PathVariable("id") Long id) {
        var video = videoService.getVideo(id);
        if (video == null) {
            return new ResponseEntity<>(
                    new ExceptionHandling(
                            "Video is not available with this = " + id),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(video, HttpStatus.OK);
        }
    }

    // create new video
    @PostMapping("/new")
    public ResponseEntity<?> createNewVideo(@RequestBody Video video) {
        var newVideo = videoService.createVideo(video);
        return ResponseEntity.ok().build();
    }

    // update existing video
     @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideo(@PathVariable("id") Long id,@RequestBody Video video){
         var video1= videoService.updateVideo(id,video);
         if(video1 !=null)
             return new ResponseEntity<>(video,HttpStatus.OK);
         else
             return new ResponseEntity<>(new ExceptionHandling("video is not found"),HttpStatus.NOT_FOUND);

     }

}