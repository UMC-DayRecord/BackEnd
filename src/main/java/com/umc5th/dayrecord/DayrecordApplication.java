package com.umc5th.dayrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DayrecordApplication {
	public static void main(String[] args) {
		SpringApplication.run(DayrecordApplication.class, args);
	}

}
