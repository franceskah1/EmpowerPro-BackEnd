package com.example.empowerprobackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ChangePasswordDTO {
    private final String password;
    private final String newPassword;
}
