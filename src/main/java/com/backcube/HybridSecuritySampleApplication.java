package com.backcube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.vaadin.spring.events.annotation.EnableVaadinEventBus;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableVaadinEventBus
public class HybridSecuritySampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridSecuritySampleApplication.class, args);
    }
}
