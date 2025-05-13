package com.axionlabs.arkive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Arkive API Documentation",
				description = "Comprehensive documentation for the Arkive APIs, supporting secure user login and file uploads to Amazon S3.",
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
public class ArkiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArkiveApplication.class, args);
	}

}
