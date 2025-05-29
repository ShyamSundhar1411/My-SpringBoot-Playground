package com.axionlabs.todowoo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "TodoWoo API Documentation",
				description = "Comprehensive documentation for the TodoWoo APIs, supporting secure user login and todo list.",
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
public class TodowooApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodowooApplication.class, args);
	}

}
