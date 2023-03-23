package miu.videokabbee.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import miu.videokabbee.domain.Contact;
import miu.videokabbee.domain.Users;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder


// user DTO
public class UserDTO {



        @NotBlank(message="first name cant be null")
        private String firstName;
        @NotBlank(message="last name cant be null")
        private String lastName;
        @Size( min=8 ,max=10 ,message = "password should be between 8 and 10")
        @NotBlank(message="password should not be blank")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
                message = "Password must contain at least one uppercase letter, " +
                        "one lowercase letter, one number, and one special character")
        private String password;
        @Email
        private String email;
        @Digits(integer = 2,
                fraction = 0,
                message = "Age must be a 1 or 2-digit number")
        private  Integer age;

//    @Autowired
    /**
     * autowiring static methods is not needed*/

    private static ModelMapper modelMapper;
        //
        public static List<UserDTO> listUsersDto(List<Users> users) {
            return users
                    .stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
        }
        public static UserDTO toDTo(Users user) {
            return UserDTO.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .age(user.getAge())
                    .password(user.getPassword())
                    .email(user.getContact().getEmail())

                    .build();
        }public static Users  toUsers(UserDTO user) {
            return   Users.builder()
                     .firstName(user.getFirstName())
                     .lastName(user.getLastName())
                     .password(user.getPassword())
                     .contact(new Contact(builder().email))
                     .build();

        }
}



