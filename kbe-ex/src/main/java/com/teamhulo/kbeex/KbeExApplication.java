package com.teamhulo.kbeex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class KbeExApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbeExApplication.class, args);
	}
}