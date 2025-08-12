package com.policy360.auth.dto;

import com.policy360.auth.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {

    private String token;

    //private long expiresIn; //milliseconds

    private String tokenType;  // e.g., "Bearer"

    private Role role;


}
