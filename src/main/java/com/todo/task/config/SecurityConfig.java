package com.todo.task.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final FirebaseJwtFilter firebaseJwtFilter;
 
    public SecurityConfig(FirebaseJwtFilter firebaseJwtFilter) {
        this.firebaseJwtFilter = firebaseJwtFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {

        http.addFilterBefore(firebaseJwtFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/public/**").permitAll()
                .anyRequest().authenticated()              
            );

        return http.build();
    }
}
