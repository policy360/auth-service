package com.policy360.auth.feign;

import com.policy360.auth.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {

    @PostMapping("/api/customers")
    CustomerDto createCustomer(@RequestBody CustomerDto customerDto);
}
