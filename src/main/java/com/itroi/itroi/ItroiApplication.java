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

            ProductServiceImpl productService = new ProductServiceImpl();
            CartServiceImpl cartService = new CartServiceImpl(productService);
		    UserServiceImpl userService=new UserServiceImpl(cartService);

            Endpoint.publish("http://localhost:8081/ws/carts?wsdl", cartService);
            Endpoint.publish("http://localhost:8081/ws/products?wsdl", productService);
            Endpoint.publish("http://localhost:8081/ws/users?wsdl",userService);
            System.out.println("SOAP Web Services is running...");

		};
	}
}
