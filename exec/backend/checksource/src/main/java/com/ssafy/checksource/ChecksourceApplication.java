package com.ssafy.checksource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ChecksourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecksourceApplication.class, args);
	}

}
