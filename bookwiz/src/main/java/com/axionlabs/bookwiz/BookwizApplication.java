package com.axionlabs.bookwiz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "BookWiz REST API Documentation",
				description = "REST API Documentation for BookWiz",
				version = "v1",
				contact = @Contact(
						name = "Shyam Sundhar",
						email = "clashwithchiefrpjyt@gmail.com",
						url = "https://shyamsundhar.vercel.app"
				),
				license = @License(
						name = "MIT"
				)
		)
)
public class BookwizApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookwizApplication.class, args);
	}

}
