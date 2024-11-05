package com.example.springsecurity;

import com.example.springsecurity.dto.CreateUserRequest;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.service.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringsecurityApplication implements CommandLineRunner {

	private final UserServiceIF userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	private void createDummyData() {
		CreateUserRequest request = CreateUserRequest.builder()
				.name("Furkan")
				.username("furkan")
				.password("pass")
				.authorities(Set.of(Role.USER))
				.build();
		userService.createUser(request);

		CreateUserRequest request2 = CreateUserRequest.builder()
				.name("Mehmet")
				.username("mehmet")
				.password("pass")
				.authorities(Set.of(Role.ADMIN))
				.build();
		userService.createUser(request2);
	}
}
