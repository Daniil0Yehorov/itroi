package com.itroi.itroi;

import com.itroi.itroi.generated_models.Product;
import com.itroi.itroi.generated_models.User;
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
			Product product1 = new Product();
			product1.setCategory("Dog Food");
			product1.setCountInStock(50);
			product1.setDescription("Premium dog food");
			product1.setID(1);
			product1.setName("Royal Canin");
			product1.setPrice(30.0);
			productService.addProduct(product1);

			Product product2 = new Product();
			product2.setCategory("Cat Food");
			product2.setCountInStock(30);
			product2.setDescription("Healthy cat food");
			product2.setID(2);
			product2.setName("Whiskas");
			product2.setPrice(20.0);
			productService.addProduct(product2);

			Product product3 = new Product();
			product3.setCategory("Bird Food");
			product3.setCountInStock(15);
			product3.setDescription("Nutrient-rich food for birds");
			product3.setID(3);
			product3.setName("Versele-Laga");
			product3.setPrice(10.0);
			productService.addProduct(product3);
			User admin = new User();
			admin.setEmail("admin@example.com");
			admin.setID(userService.generateUniqueUserId());
			admin.setLogin("admin");
			admin.setName("Admin User");
			admin.setPassword("admin123");
			admin.setPhone("123-456-7890");
			admin.setType("Admin");
			userService.createUser(admin);

			User user1 = new User();
			user1.setEmail("user1@example.com");
			user1.setID(userService.generateUniqueUserId());
			user1.setLogin("user1");
			user1.setName("User One");
			user1.setPassword("password1");
			user1.setPhone("321-654-0987");
			user1.setType("User");
			userService.createUser(user1);

			User user2 = new User();
			user2.setEmail("user2@example.com");
			user2.setID(userService.generateUniqueUserId());
			user2.setLogin("user2");
			user2.setName("User Two");
			user2.setPassword("password2");
			user2.setPhone("987-654-3210");
			user2.setType("User");
			userService.createUser(user2);
		};
	}
}
