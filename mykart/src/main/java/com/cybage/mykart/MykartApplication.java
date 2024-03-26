package com.cybage.mykart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ="com.cybage",exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.cybage.repository")
@EntityScan("com.cybage.model")
public class MykartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MykartApplication.class, args);
	}

}
