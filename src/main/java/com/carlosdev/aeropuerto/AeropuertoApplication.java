package com.carlosdev.aeropuerto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AeropuertoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeropuertoApplication.class, args);
	}

}
