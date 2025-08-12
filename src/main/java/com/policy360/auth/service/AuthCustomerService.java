package com.policy360.auth.service;

import com.policy360.auth.dto.CustomerDto;
import com.policy360.auth.feign.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCustomerService {
    private final CustomerServiceClient customerServiceClient;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        return customerServiceClient.createCustomer(customerDto);
    }
}
