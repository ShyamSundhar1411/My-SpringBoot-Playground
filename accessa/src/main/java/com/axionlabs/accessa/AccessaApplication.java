package com.axionlabs.accessa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Accessa API Documentation",
				description = "Comprehensive documentation for the Accessa authentication APIs, supporting secure user registration, login, and role-based access using JWT.",
				version = "v1.0",
				contact = @Contact(
						name = "Shyam Sundhar",
						email = "clashwithchiefrpjyt@gmail.com",
						url = "https://shyamsundhar.vercel.app"
				),
				license = @License(
						name = "MIT License",
						url = "https://opensource.org/licenses/MIT"
				)
		)
)
public class AccessaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessaApplication.class, args);
	}

}
