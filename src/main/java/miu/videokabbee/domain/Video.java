package miu.videokabbee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Video {
    @Id
    private String videoId;
    private String title;
    private String url;
    private boolean isPublic;
    private LocalDate expirationDate;





    public Video(String title, String url, boolean isPublic, LocalDate expirationDate) {
        this.title = title;
        this.url = url;
        this.isPublic = isPublic;
        this.expirationDate = expirationDate;
    }


    public void put(String url, Video video) {
        // Check if the video already exists in the system
        if (video.equals(video)) {
            throw new RuntimeException("Video with URL " + url + " already exists");
        }

        // Create a new video object and add it to the videos map
        Video videos = new Video(title, url, isPublic, expirationDate);
        videos.put(url, video);
    }
}
