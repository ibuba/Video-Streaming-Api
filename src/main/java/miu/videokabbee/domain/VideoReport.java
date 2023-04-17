package miu.videokabbee.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoReport {
    private Long id;

    @NotBlank(message="video title cant be null")
    private String videoTitle;
    private String  customerEmail;
    @NotBlank(message="report  cant be null")
    private  String report;

}
