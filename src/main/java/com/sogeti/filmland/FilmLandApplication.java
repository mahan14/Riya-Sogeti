package com.sogeti.filmland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.sogeti.filmland")
public class FilmLandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmLandApplication.class, args);
	}

}