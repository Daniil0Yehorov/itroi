package com.itroi.itroi;

import com.itroi.itroi.serviceimpl.cartImplementation;
import com.itroi.itroi.serviceimpl.productImplementation;
import com.itroi.itroi.serviceimpl.userImplementation;
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

            productImplementation productService = new productImplementation();
            cartImplementation cartService = new cartImplementation(productService);
		    userImplementation userService=new userImplementation(cartService);

            Endpoint.publish("http://localhost:8081/ws/carts?wsdl", cartService);
            Endpoint.publish("http://localhost:8081/ws/products?wsdl", productService);
            Endpoint.publish("http://localhost:8081/ws/users?wsdl",userService);
            System.out.println("SOAP Web Services is running...");

		};
	}
}
