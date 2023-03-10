package miu.videokabbee.config;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
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

//    private final static List<UserDetails> ApplicationUsers= Arrays.asList(
//            new User(
//                    "abule@gmail",
//                    "password",
//                    Collections.singleton(new SimpleGrantedAuthority("Role_Admin"))
//
//            ),
//
//            new User(
//                    "dane@gmail",
//                    "passwords",
//                    Collections.singleton(new SimpleGrantedAuthority("Role_USer"))
//
//            )
//    );

      public final UserRepository userRepository;

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

               var user=userRepository.findByContactEmail(email);

               if(user.isPresent()){

                   Users user1=user.get();

//                   UserDetails userDetails=new User(user1.getContact().getEmail(),user1.getPassword(),
//                           Collections.singleton(new SimpleGrantedAuthority(user1.getRole())));


                   return new User(user1.getContact().getEmail(),user1.getPassword(),
                           Collections.singleton(new SimpleGrantedAuthority(user1.getRole())));
               }else{
                   return null;
               }


//                return ApplicationUsers
//                        .stream()
//                        .filter(u->u.getUsername().equals(email))
//                        .findFirst()
//                        .orElseThrow(()->new UsernameNotFoundException("No user found"))
//                        ;
            }
        }

