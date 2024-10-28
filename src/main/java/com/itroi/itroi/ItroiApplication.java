package com.itroi.itroi;

import com.itroi.itroi.ServiceImplementation.CartServiceImpl;
import com.itroi.itroi.ServiceImplementation.ProductServiceImpl;
import com.itroi.itroi.ServiceImplementation.UserServiceImpl;
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
			Endpoint.publish("http://localhost:8081/ws/carts?wsdl", new CartServiceImpl());
			Endpoint.publish("http://localhost:8081/ws/products?wsdl", new ProductServiceImpl());
			Endpoint.publish("http://localhost:8081/ws/users?wsdl", new UserServiceImpl());
			System.out.println("SOAP Web Services is running...");
		};
	}
}
