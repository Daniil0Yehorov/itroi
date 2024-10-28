package com.itroi.itroi;

import jakarta.xml.ws.Endpoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ItroiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItroiApplication.class, args);

	}
	@Bean
	public CommandLineRunner publishWebService() {
		return args -> {
			// web service to work with products for animals
			//Endpoint.publish("http://localhost:8081/ws/Products?wsdl", new ());
			System.out.println("SOAP Web Service for Products is published at http://localhost:8081/ws/Products?wsdl");

			// web service to work with users
			//Endpoint.publish("http://localhost:8081/ws/users?wsdl", new ());
			System.out.println("SOAP Web Service for Users is published at http://localhost:8081/ws/users?wsdl");

			////////////////////
		};
	}
}
