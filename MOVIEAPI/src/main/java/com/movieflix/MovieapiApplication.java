package com.movieflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.movieflix.controllers", "com.movieflix.service"})
public class MovieapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieapiApplication.class, args);
	}

}
