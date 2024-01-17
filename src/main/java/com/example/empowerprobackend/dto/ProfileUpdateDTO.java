package com.example.empowerprobackend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileUpdateDTO {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Long dateOfBirth;
    String placeOfBirth;
    String address;
    MultipartFile photo;
}
