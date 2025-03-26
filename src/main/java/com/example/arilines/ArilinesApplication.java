package com.example.arilines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.example.arilines", "com.speedment.jpastreamer"})
public class ArilinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArilinesApplication.class, args);
	}

}
