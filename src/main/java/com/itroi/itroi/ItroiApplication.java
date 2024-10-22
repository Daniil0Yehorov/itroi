package com.itroi.itroi;

import Model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication
public class ItroiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItroiApplication.class, args);

	}

}
