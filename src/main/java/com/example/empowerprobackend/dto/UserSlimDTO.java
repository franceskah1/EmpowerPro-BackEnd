package com.example.empowerprobackend.dto;

import com.example.empowerprobackend.models.Role;
import lombok.Data;

@Data
public class UserSlimDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private Role role;
}
