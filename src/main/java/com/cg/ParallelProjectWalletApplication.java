package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.cg.repository")
@SpringBootApplication
public class ParallelProjectWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParallelProjectWalletApplication.class, args);
	}

}
