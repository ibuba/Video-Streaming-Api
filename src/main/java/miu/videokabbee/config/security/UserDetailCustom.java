package miu.videokabbee.config.security;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.Role;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@Service
@RequiredArgsConstructor

public class UserDetailCustom implements UserDetailsService {


    public final UserRepository userRepository;

    // User To user Details changing  Method

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

               var user=userRepository.findByContactEmail(email);

               if(user.isPresent()){

                   Users user1=user.get();
                   List<GrantedAuthority> authorities = new ArrayList<>();
                   for (Role role : user1.getRole()) {
                       authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                   }

                   return new User(user1.getContact().getEmail(),user1.getPassword(),authorities);
                          // Collections.singleton(new SimpleGrantedAuthority(user1.getRole())));
               }else{
                   return null;
               }
            }
        }

