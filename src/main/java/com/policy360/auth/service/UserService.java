package com.policy360.auth.service;

import com.policy360.auth.dto.*;


public interface UserService {

    UserResponseDto registerCustomer(RegistrationRequestDto requestDto);
    AuthResponseDto login(LoginRequestDto requestDto);
    UserDto getUserById(Long userId);
}
