package com.example.empowerprobackend.dto;
import com.example.empowerprobackend.models.Departments;
import com.example.empowerprobackend.models.Role;
import lombok.*;
import java.time.LocalDate;

@Data
public class RegistrationDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private Departments departments;
    private String phoneNumber;
    private LocalDate hireDate;
    private String address;
    private LocalDate birthdate;
    private String birthplace;
    private String gender;
    // private Boolean isEnabled=false;
}
