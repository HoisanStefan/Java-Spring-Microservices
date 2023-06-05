package com.awbd2.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.awbd2.controller", "com.awbd2.service"})
@EntityScan("com.awbd2.entity")
@EnableJpaRepositories("com.awbd2.repository")
public class CustomerServiceApplication {
    @Value("${card.service.url}")
    private String cardServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient () {
        WebClient webClient =WebClient.builder()
                .baseUrl(cardServiceUrl).build();

        return webClient;
    }
}
