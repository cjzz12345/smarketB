package com.flagcamp.ehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/register", "/login", "/logout", "/sell", "/cart", "/checkout").permitAll()
                .requestMatchers(HttpMethod.GET, "/search", "/cart").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/sell/*", "/cart/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
