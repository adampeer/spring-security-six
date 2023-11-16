package com.springboot.springsecuritysix;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.springsecuritysix.entity.Role;
import com.springboot.springsecuritysix.entity.User;
import com.springboot.springsecuritysix.repository.RoleRepository;
import com.springboot.springsecuritysix.service.UserServiceInterface;
import com.springboot.springsecuritysix.utils.StringUtils;

@SpringBootApplication
public class SpringsecuritysixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritysixApplication.class, args);
	}

	private UserServiceInterface userServiceInterface;

	public SpringsecuritysixApplication(UserServiceInterface userServiceInterface) {
		this.userServiceInterface = userServiceInterface;
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, PasswordEncoder passwordEncode) {

		return args -> {

			Set<Role> adminRole = Stream.of(new Role(StringUtils.ADMIN)).collect(Collectors.toSet());
			Set<Role> userRole = Stream.of(new Role(StringUtils.USER)).collect(Collectors.toSet());

			// Revoked compromised password and changed to a secure one
			User admin = new User(1L, "John", "Doe", "john.doe@email.com", "password", adminRole);
			User user = new User(2L, "Jim", "Doe", "jim.doe@email.com", "password", userRole);

			userServiceInterface.save(admin);
			userServiceInterface.save(user);
		};
	}

}