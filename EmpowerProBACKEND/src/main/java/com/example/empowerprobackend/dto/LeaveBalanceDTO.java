package com.example.empowerprobackend.dto;
import lombok.Data;
@Data
public class LeaveBalanceDTO {
    private Long id;
    private int totalDay;
    private int spentDay;
    private int remainDay;
    private Long userId;
}
