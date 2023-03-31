package miu.videokabbee.domain;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PolicyUpdateNotification {


    private String title;
    private String message;
    private LocalDate date;


}
