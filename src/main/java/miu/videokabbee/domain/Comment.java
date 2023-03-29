package miu.videokabbee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comment {
    @Id
    private String commentId;
    private String userId;
    private String videoId;
    private String text;
    private Date timestamp;
    private List<Comment> replies;
}
