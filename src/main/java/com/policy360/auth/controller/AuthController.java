package com.policy360.auth.controller;

import com.policy360.auth.dto.*;
import com.policy360.auth.feign.PolicyServiceClient;
import com.policy360.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.PrimitiveIterator;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  final UserService userService;

    @Autowired
    private PolicyServiceClient policyServiceClient;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ping-policy")
    public String pingPolicy() {
        return policyServiceClient.pingPolicyService();
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegistrationRequestDto requestDto) {
        var user = userService.registerCustomer(requestDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        AuthResponseDto authResponseDto = userService.login(requestDto);
        return ResponseEntity.ok(authResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
