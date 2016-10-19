package com.backcube.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.PrototypeScope;

/**
 * Created by jimbriglio on 10/16/16.
 */
@Component
public class Beans {

    @Bean(name= "passwordEncoder")
    @PrototypeScope
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}
