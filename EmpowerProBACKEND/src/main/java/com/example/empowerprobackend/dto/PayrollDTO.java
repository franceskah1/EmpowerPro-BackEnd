package com.example.empowerprobackend.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class PayrollDTO {
    private Long id;
    private double salary;
    private double bonus;
   // private double totalSalary;
    private LocalDate payrollDate;
    private Long userId;

}
