package com.neoapp.clienteapi.neoapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ComponentScan(basePackages = {
    "com.neoapp.clienteapi.neoapp",
    "com.neoapp.clienteapi.security"
})
@SpringBootApplication
public class NeoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoappApplication.class, args);
	}

}
