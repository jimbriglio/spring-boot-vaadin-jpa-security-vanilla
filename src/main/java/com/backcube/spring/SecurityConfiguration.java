package com.backcube.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by jimbriglio on 10/16/16.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //@formatter:off
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email,password_hash,true from user where email=?")
                .authoritiesByUsernameQuery(
                        "select email,role from user where email=?")
                .passwordEncoder(passwordEncoder);
//        auth
//                    .inMemoryAuthentication()
//                    .withUser("admin").password("p").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("p").roles("USER");
        //@formatter:on
    }

    static {
        // Use a custom SecurityContextHolderStrategy
        SecurityContextHolder.setStrategyName(VaadinSessionSecurityContextHolderStrategy.class.getName());
    }
}
