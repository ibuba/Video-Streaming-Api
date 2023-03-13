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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
       // ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)


        http
                .csrf().disable()
                        .authorizeHttpRequests()
                .requestMatchers("/api/auth/**")
                //.antMatchers()

                .permitAll()
                .requestMatchers("/unAuthorized")
                .permitAll()
                .requestMatchers("/user/register/**")
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

//        http
//                .csrf().disable()
//                        .authorizeHttpRequests()
//                .requestMatchers("/api/auth/**")
//                //.antMatchers()
//
//                .permitAll()
//                .requestMatchers("/user/register/**")
//                .permitAll()
//                        .anyRequest()
//                        .authenticated()
//                        .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                        ;

        System.out.println("abule");
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
       final DaoAuthenticationProvider authenticationProvider=
               new DaoAuthenticationProvider();
       authenticationProvider
               .setUserDetailsService(userDetailsService);
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       return  authenticationProvider;
    }
@Bean
    public PasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder();
    return NoOpPasswordEncoder.getInstance();
    }

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
}





//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                return ApplicationUsers
//                        .stream()
//                        .filter(u->u.getUsername().equals(email))
//                        .findFirst()
//                        .orElseThrow(()->new UsernameNotFoundException("No user found"))
//                        ;
//            }
//        };
    }

//    @Bean
//
//    public SecurityFilterChain ssecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        //http.formLogin();
//        //http.httpBasic();
//        return (SecurityFilterChain)http.build();
   // }
//}
