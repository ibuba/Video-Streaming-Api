package miu.videokabbee.config.security;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@Service
@RequiredArgsConstructor

public class UserDetailCustom implements UserDetailsService {

@Autowired
    public final UserRepository userRepository;

    // User To user Details changing  Method

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

               var user=userRepository.findByContactEmail(email);

               if(user.isPresent()){

                   Users user1=user.get();

                   return new User(user1.getContact().getEmail(),user1.getPassword(),
                           Collections.singleton(new SimpleGrantedAuthority(user1.getRoles().getId())));//just changed user1.getRole()
               }else{
                   return null;
               }
            }
        }

