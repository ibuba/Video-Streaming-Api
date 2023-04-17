package miu.videokabbee.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
@Setter
@Getter
public class ReportDTO {
    private Long id;
    @NotBlank(message="Report  cant be null")
    private String report;
    @NotBlank(message="video-title cant be null")
    private String VideoTitle;
}
