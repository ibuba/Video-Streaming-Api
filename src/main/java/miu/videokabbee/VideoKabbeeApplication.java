package miu.videokabbee;

import miu.videokabbee.domain.Address;
import miu.videokabbee.domain.Contact;
import miu.videokabbee.domain.Users;
import miu.videokabbee.service.UserInterfaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VideoKabbeeApplication implements CommandLineRunner {
	@Autowired
	UserInterfaceService userInterfaceService;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(VideoKabbeeApplication.class, args);
	}@Override
	public void run(String... args) throws Exception {
		Contact contact= new Contact("+15205994323","ibrahim.imam642@gmail.com");
		Contact contact1= new Contact("+15205778890","imam642@gmail.com");
		Address address=new Address("s","city","ca","12334");
		Address address1=new Address("400w Washington ","Fairfield",
				                      "IA","52556");
		Users user1=new Users(1L,"Ibrahim","Imam",31,"ADMIN",
				 passwordEncoder.encode( "I@ibrahim1"),contact,address);
		Users user2=new Users(2L,"abi","zaki",45,"ADMIN"
				, passwordEncoder.encode( "A@bule23"),contact1,address1);
		userInterfaceService.register(user1);
		userInterfaceService.register(user2);
	}

}
