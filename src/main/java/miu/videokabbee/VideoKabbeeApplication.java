package miu.videokabbee;

import miu.videokabbee.domain.Address;
import miu.videokabbee.domain.Contact;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.service.UserInterfaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication()
public class VideoKabbeeApplication implements CommandLineRunner {

	@Autowired
	UserInterfaceService userInterfaceService;

	public static void main(String[] args) {

		SpringApplication.run(VideoKabbeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Contact contact= new Contact("+15205994323","abule@gmail.com");
		Address address=new Address("s","city","ca","12334");
		Users aa=new Users(1L,"abi","zaki",45, List.of(new Role(
				1L,"ADMIN"),new Role(2L,"GUEST")),"abule",
				"1234",contact,address);

		userInterfaceService.register(aa);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
