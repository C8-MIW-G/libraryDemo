package nl.miwgroningen.se8.vincent.libraryDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

}
