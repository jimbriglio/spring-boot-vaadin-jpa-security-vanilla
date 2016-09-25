package com.backcube;

import com.vaadin.spring.boot.annotation.EnableVaadinServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class SpringBootVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVaadinApplication.class, args);
	}

}
