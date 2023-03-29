package miu.videokabbee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Document
public class Video {
    @Id
    private String videoId;
    private String title;
    private String url;
    private boolean isPublic;
    private Date expirationDate;
    private int viewCount;
    private int likeCount;
    private List<Comment> comments;



    // Constructors...

    public void incrementLikeCount() {
        likeCount++;
    }
    public Video() {
    }

    public Video(String videoId) {
        this.videoId = videoId;
    }

    public Video(String videoId, String title) {
        this.videoId = videoId;
        this.title = title;
    }

    public Video(String title, String url, boolean isPublic, Date expirationDate) {
        this.title = title;
        this.url = url;
        this.isPublic = isPublic;
        this.expirationDate = expirationDate;
    }


    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
}
