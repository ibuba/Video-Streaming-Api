package miu.videokabbee;

import miu.videokabbee.domain.Address;
import miu.videokabbee.domain.Contact;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.RoleRepository;
import miu.videokabbee.service.UserInterfaceService;
import miu.videokabbee.service.UserServiceImpl.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
public class VideoKabbeeApplication implements CommandLineRunner {


    @Autowired
    private UserInterfaceService userInterfaceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;


    public static void main(String[] args) {


        SpringApplication.run(VideoKabbeeApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Contact contact = new Contact("+15205994323", "abule@gmail.com");
        Address address = new Address("s", "city", "ca", "12334");
        Role userRole = roleService.saveRole(new Role());
        userRole.setName("ADMIN");
        userRole.setId(userRole.getId());
        roleRepository.save(userRole);

        Users aa = new Users(1L, "abi", "zaki", 45, "AbiZ", "abule"
                , userRole, contact, address);
        userInterfaceService.register(aa);
//		Contact contact1= new Contact("+14155994323","dan@gmail.com");
//		Address address1=new Address("100N St","San Francisco","CA","94124");
        //Role adminRole1 = roleService.saveRole(new Role(adminRole));
//		userRole1.setName(Role.ROLE_ADMIN);
//		userRole1.setDescription("Admin role");
//		Users aa1=new Users(2L,"Dan","Greatest",65,"DaniG","dani@415",adminRole1,contact1,address1);
//		userInterfaceService.register(aa1);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


}
