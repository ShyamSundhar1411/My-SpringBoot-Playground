package com.axionlabs.meldora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class MeldoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeldoraApplication.class, args);
	}

}
