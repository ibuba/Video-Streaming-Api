package miu.videokabbee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInRequest {
    private String email;
    private String password;
}
