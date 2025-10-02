package com.BT10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.BT10.Entity")
public class Bt10Application {

	public static void main(String[] args) {
		SpringApplication.run(Bt10Application.class, args);
	}

}
