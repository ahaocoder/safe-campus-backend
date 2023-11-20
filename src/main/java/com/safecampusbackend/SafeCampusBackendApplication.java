package com.safecampusbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafeCampusBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeCampusBackendApplication.class, args);
		System.out.println("------------------------安全校园，启动！------------------------");
	}

}
