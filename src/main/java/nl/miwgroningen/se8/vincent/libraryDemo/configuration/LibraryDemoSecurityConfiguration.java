package nl.miwgroningen.se8.vincent.libraryDemo.configuration;

import nl.miwgroningen.se8.vincent.libraryDemo.service.LibraryUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Configure the security settings for my application
 */

@Configuration
public class LibraryDemoSecurityConfiguration {

    final LibraryUserDetailService libraryUserDetailService;

    public LibraryDemoSecurityConfiguration(LibraryUserDetailService libraryUserDetailService) {
        this.libraryUserDetailService = libraryUserDetailService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                    .antMatchers("/css/**", "/webjars/**").permitAll()
                    .antMatchers("/", "/books", "/books/details/*").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin().and()
            .logout().logoutSuccessUrl("/books");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(libraryUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
