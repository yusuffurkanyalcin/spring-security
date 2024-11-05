package com.example.springsecurity.config;

import com.example.springsecurity.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        x -> x.requestMatchers("/public/**").permitAll()
                                .requestMatchers("/private/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name()))
//                .authorizeHttpRequests(x -> x.anyRequest().authenticated()) // en altta kalsın yoksa diğerlerinde de authenticated koşulu olur
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
