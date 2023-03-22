package miu.videokabbee.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contact {
    private String phone;

    public Contact(String email) {
        this.email = email;
    }

    @Email
    @NotNull(message="email should not be empty")
    private String email;

}
