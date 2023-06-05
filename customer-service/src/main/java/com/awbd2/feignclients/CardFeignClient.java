package com.awbd2.feignclients;

import com.awbd2.response.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "card-service", path = "/api/card")
public interface CardFeignClient {
    @GetMapping("/getById/{id}")
    CardResponse getById(@PathVariable long id);
}
