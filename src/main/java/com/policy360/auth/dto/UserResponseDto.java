package com.policy360.auth.dto;

import com.policy360.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private String username;
    private String fullName;
    private String email;
    private String mobile;
    private LocalDate dateOfBirth;
    private String address;
    private String role;
    private LocalDateTime createdAt;
    private String status;

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@"))
            return  email;
        String[] parts = email.split("@");
        return parts[0].charAt(0) + "****@" + parts[1];
    }

    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length()<10) return mobile;
        return "*****" + mobile.substring(mobile.length()-5);
    }

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(maskEmail(user.getEmail()))
                .mobile(maskMobile(user.getMobile()))
                .dateOfBirth(user.getDateOfBirth())
                .address(user.getAddress())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .status(user.getStatus().name())
                .build();
  }
}
