package com.awbd2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.awbd2.controller", "com.awbd2.service"})
@EntityScan("com.awbd2.entity")
@EnableJpaRepositories("com.awbd2.repository")
@EnableFeignClients("com.awbd2.feignclients")
@EnableDiscoveryClient
public class CustomerServiceApplication {
    @Value("${card.service.url}")
    private String cardServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
