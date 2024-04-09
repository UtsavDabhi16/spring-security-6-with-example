package com.inexture.springsecurityexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails normalUserDetails = User.builder()
                .username("utsav")
                .password(passwordEncoder().encode("1234"))
                .roles("USER").build();

        UserDetails adminUserDetails = User.builder()
                .username("coco")
                .password(passwordEncoder().encode("4934"))
                .roles("ADMIN","USER").build();

        return new InMemoryUserDetailsManager(normalUserDetails,adminUserDetails);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
