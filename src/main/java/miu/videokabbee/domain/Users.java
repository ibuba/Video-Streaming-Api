package miu.videokabbee.domain;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Users {
    @Id
    private Long id;
    @NotBlank(message="first name cant be null")
    private String firstName;
    @NotBlank(message = "last name  should  not be null")
    private String lastName;
    @Digits(integer = 2, fraction = 0, message = "Age must be a 1 or 2-digit number")
    private int age;
<<<<<<< HEAD

    private List<Role> role =
            new ArrayList<>();
    @NotNull(message="username should not be null")
    @Size(max=20,message = "username can not be more than 20")
    private String userName;
=======
    private String role;

>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2
   @Size( min=8 ,max=10 ,message = "password should be between 8 and 10")
   @NotBlank(message="password should not be blank")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
           message = "Password must contain at least one uppercase letter, " +
                   "one lowercase letter, one number, and one special character")
   private String password;
    private Contact contact;
    private Address address;
    private String Otp;

<<<<<<< HEAD
    public Users(Long id, String firstName,
                 String lastName, int age,
                 List<Role> role, String userName,
                 String password, Contact contact,
                 Address address) {
=======
    public Users(Long id, String firstName, String lastName, int age, String role, String password, Contact contact, Address address) {
>>>>>>> d0ebfed7235935f4e1a272c3a02a701250becfb2
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.password = password;
        this.contact = contact;
        this.address = address;
    }
}
