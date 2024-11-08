package com.example.springsecurity;

import com.example.springsecurity.service.UserDetailsServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringsecurityApplication {

	private final UserDetailsServiceIF userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
}
