package nl.miwgroningen.se8.vincent.libraryDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Configure the security settings for my application
 */

@Configuration
public class LibraryDemoSecurityConfiguration {

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
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("$2a$10$t3oYUvyNmLwd6nvspbgB3umTC7F.xuv4X9vNxCDVPhAF1Zk2h6SXK")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
