package com.example.simplesaga.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class SimpleOrderSagaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleOrderSagaUserApplication.class, args);
    }
}
