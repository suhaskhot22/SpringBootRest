package com.scp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.scp"})
@EnableJpaRepositories("com.scp.repository")
@EntityScan("com.scp.model")

public class SpringBootWebBasedApplication {
	public static void main(String args[])
	{
		SpringApplication.run(SpringBootWebBasedApplication.class, args);
	}

}
