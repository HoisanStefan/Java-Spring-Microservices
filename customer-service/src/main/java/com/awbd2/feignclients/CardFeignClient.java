package com.awbd2.feignclients;

import com.awbd2.response.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
public interface CardFeignClient {
    @GetMapping("/card-service/api/card/getById/{id}")
    CardResponse getById(@PathVariable long id);
}
