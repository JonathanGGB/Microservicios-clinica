package com.clinic.studies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class StudiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudiesApplication.class, args);
	}

}
