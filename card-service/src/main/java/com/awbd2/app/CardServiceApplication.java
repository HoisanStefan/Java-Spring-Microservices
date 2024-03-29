package com.awbd2.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.awbd2.controller", "com.awbd2.service"})
@EntityScan("com.awbd2.entity")
@EnableJpaRepositories("com.awbd2.repository")
@EnableDiscoveryClient
@RefreshScope
public class CardServiceApplication {
    @Value("${address.exchangeApi.url}")
    private String exchangeApiUrl;

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient () {
        WebClient webClient = WebClient.builder()
                .baseUrl(exchangeApiUrl).build();

        return webClient;
    }
}
