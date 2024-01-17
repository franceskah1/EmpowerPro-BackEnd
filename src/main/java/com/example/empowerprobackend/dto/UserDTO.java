package com.example.empowerprobackend.dto;
import com.example.empowerprobackend.models.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
@Data
public class UserDTO {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String birthplace;
    private String phoneNumber;
    private LocalDate hireDate;
    private String address;
    private MultipartFile photo;
    private Role role;
    private byte[] bytePhoto;
    private String gender;
    private String FileName;
    private String FileType;



}
