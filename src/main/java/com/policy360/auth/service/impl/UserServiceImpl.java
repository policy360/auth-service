package com.policy360.auth.service.impl;

import com.policy360.auth.dto.*;
import com.policy360.auth.entity.User;
import com.policy360.auth.enums.AccountStatus;
import com.policy360.auth.enums.Role;
import com.policy360.auth.exception.UserAlreadyExistsException;
import com.policy360.auth.exception.UserNotFoundException;
import com.policy360.auth.repository.UserRepository;
import com.policy360.auth.util.JwtUtil;
import com.policy360.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto registerCustomer(RegistrationRequestDto requestDto) {

            if (userRepository.existsByUsername(requestDto.getUsername())) {
                throw new UserAlreadyExistsException("Username already taken");
            }
            if (userRepository.existsByEmail(requestDto.getEmail())) {
                throw new UserAlreadyExistsException("Email already registered");
            }


            User user = User.builder()
                    .username(requestDto.getUsername())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .fullName(requestDto.getFullName())
                    .email(requestDto.getEmail())
                    .mobile(requestDto.getMobile())
                    .dateOfBirth(requestDto.getDateOfBirth())
                    .address(requestDto.getAddress())
                    .nationalId(requestDto.getNationalId())
                    .role(Role.ROLE_CUSTOMER)
                    .status(AccountStatus.ACTIVE)
                    .build();
            User savedUser = userRepository.save(user);
            return UserResponseDto.fromEntity(savedUser);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new AuthResponseDto(token, "Bearer",user.getRole());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with userId " + userId + " is not found."));

        UserDto userDto = UserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getFullName())
                .build();
        return userDto;

    }
}
