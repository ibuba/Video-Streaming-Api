package miu.videokabbee.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInRequest {
    @Email
    @NotNull(message = "message should not  be  empty")
    private String email;
    @Size( min=8 ,max=10 ,message = "password should be between 8 and 10")
    @NotBlank(message="password should not be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;
}
