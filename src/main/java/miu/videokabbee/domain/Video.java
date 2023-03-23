package miu.videokabbee.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Video {
    @Id
    private Long id;
    private String title;
    private String videoDescription;
    private String coverImage;
    private String language;
}
