package com.example.empowerprobackend.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class RegisterRequest {
    private String password;
    private final LocalDateTime confirmationDate = LocalDateTime.now();

}
