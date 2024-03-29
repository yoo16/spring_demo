package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.formLogin(login -> login
        //         .loginProcessingUrl("/auth")
        //         .loginPage("/login")
        //         .defaultSuccessUrl("/admin/")
        //         .failureUrl("/login?error")
        //         .permitAll()
        // ).logout(logout -> logout
        //         .logoutSuccessUrl("/")
        // ).authorizeHttpRequests(authz -> authz
        //         // static contents
        //         .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        //         .requestMatchers("/admin/**").authenticated()
        //         .anyRequest().permitAll()
        //         // .requestMatchers("/admin").hasRole("ADMIN")
        //         // .requestMatchers("/", "/hello/**", "/article/**", "/api/article/**").permitAll()
        //         // .anyRequest().authenticated()
        // );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}