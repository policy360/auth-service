package com.policy360.auth.feign;

import com.policy360.auth.dto.Policy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("POLICY-SERVICE")
public interface PolicyServiceClient {


    @GetMapping("/api/policies/ping")
    String pingPolicyService();


    @PostMapping("/api/policies")
    Policy createPolicy(@RequestBody Policy policy);

    @GetMapping("/api/policies/{id}")
    Policy getPolicyById(@PathVariable("id") Long id);

    @GetMapping("/api/policies")
    List<Policy> getAllPolicies(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size);

    @PutMapping("/api/policies/{id}")
    Policy updatePolicy(@PathVariable("id") Long id, @RequestBody Policy policy);

    @DeleteMapping("/api/policies/{id}")
    void deletePolicy(@PathVariable("id") Long id);

    @GetMapping("/api/policies/customer/{customerId}")
    List<Policy> getPoliciesByCustomerId(@PathVariable("customerId") Long customerId);

}
