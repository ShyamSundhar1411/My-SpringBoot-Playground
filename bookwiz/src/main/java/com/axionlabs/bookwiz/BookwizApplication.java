package com.axionlabs.bookwiz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "BookWiz API Documentation",
				description = "Comprehensive documentation for the BookWiz REST APIs, enabling efficient management of books within the platform.",
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

public class BookwizApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookwizApplication.class, args);
	}

}
