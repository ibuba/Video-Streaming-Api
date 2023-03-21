package miu.videokabbee.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserDetailCustom userDetailsService;

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                        .authorizeHttpRequests()
                .requestMatchers("/api/auth/**")
                .permitAll()
<<<<<<< HEAD
//                .requestMatchers("/unAuthorized")
//                .permitAll()
=======
                //.requestMatchers("/unAuthorized")
                //.permitAll()
>>>>>>> ba75bc6b6734c38dda88ea4c40ab3229737c6800
                .requestMatchers("/user/register/**")
                .permitAll()

                .requestMatchers("/user/{id}").hasAuthority("ADMIN")

                        .anyRequest()
                        .authenticated()
;
    http
                .csrf().disable()
                        .authorizeHttpRequests()
            .requestMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/api/public/**").permitAll().
              // .permitAll()
               .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
            .requestMatchers("/**")
                .permitAll()
                        .anyRequest()
                       .authenticated()

                        .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                        ;
                       ;

        System.out.println("abule");
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
       final DaoAuthenticationProvider authenticationProvider=
               new DaoAuthenticationProvider();
       authenticationProvider
               .setUserDetailsService(userDetailsService);
       authenticationProvider.setPasswordEncoder(passwordEncoder());
        System.out.println("in the authentication ");
       return  authenticationProvider;
    }
@Bean

    public BCryptPasswordEncoder passwordEncoder() throws Exception {
       return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    }


