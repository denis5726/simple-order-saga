package com.example.simplesaga.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SimpleOrderSagaOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleOrderSagaOrderApplication.class, args);
    }
}
