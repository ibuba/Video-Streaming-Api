package miu.videokabbee.service.UserServiceImpl;


import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserInterfaceService {

    @Autowired
  UserRepository userRepository;



    public String register(Users users) {
        if(userRepository.existsByContact_Email(users.getContact().getEmail())){
            return "Email-taken";
        } else if (userRepository.existsByUserName(users.getUserName())) {
            return "Username-taken";

        }
          userRepository.save(users);
        return "Success";
    }


    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


}
