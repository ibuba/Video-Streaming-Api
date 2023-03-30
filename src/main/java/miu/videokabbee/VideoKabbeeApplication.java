package miu.videokabbee;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.*;
import miu.videokabbee.repository.VideoRepository;
import miu.videokabbee.service.UserInterfaceService;
import miu.videokabbee.service.roleService.RoleService;
import miu.videokabbee.service.videoservice.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class VideoKabbeeApplication implements CommandLineRunner {
	private final UserInterfaceService userInterfaceService;

	private final RoleService roleService;

	private final PasswordEncoder passwordEncoder;
	private final VideoService videoService;
	private final VideoRepository videoRepository;

	public static void main(String[] args) {

		SpringApplication.run(VideoKabbeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Contact contact = new Contact("+15205994323", "abule@gmail.com");
		Address address = new Address("s", "city", "ca", "12334");


			Users aa = new Users(1L, "abi", "zaki", 45,List.of(new Role(1l,"ADMIN")),

					 passwordEncoder.encode("Test@1234"), contact, address);


		Users aa2 = new Users(1L, "abi", "zaki", 45, List.of(new Role(
				1L, "ADMIN"), new Role(2L, "GUEST")),
				passwordEncoder.encode("1234"), contact, address);
		userInterfaceService.register(aa);


			LocalDate localDate=LocalDate.now();


			//Vide video = new Video("1",)
			Video video= new Video("1","https://kabbee.com/shanga-group-video.mp4",true,

					localDate);
			Video video1 = new Video("2","Hero","url1",true,localDate,"gener1","desc1","2 star");
			videoRepository.save(video);
			videoRepository.save(video1);
		}

	}

