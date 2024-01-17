package com.example.empowerprobackend.dto;
import lombok.Data;
import java.util.List;

@Data
public class DepartmentsDTO {
    private  Long id;
    private  String departmentName;
    private List<UserSlimDTO>users;
}
