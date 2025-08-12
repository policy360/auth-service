package com.policy360.auth.service;

import com.policy360.auth.dto.AuthResponseDto;
import com.policy360.auth.dto.LoginRequestDto;
import com.policy360.auth.dto.RegistrationRequestDto;
import com.policy360.auth.dto.UserResponseDto;


public interface UserService {

    UserResponseDto registerCustomer(RegistrationRequestDto requestDto);
    AuthResponseDto login(LoginRequestDto requestDto);
}
