package com.trello.mytrello_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MytrelloApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytrelloApiApplication.class, args);
	}

}
