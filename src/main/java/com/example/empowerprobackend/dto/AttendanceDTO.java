package com.example.empowerprobackend.dto;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AttendanceDTO {
    private Long id;
    private LocalDate attendanceDate;
    private LocalTime timeOut;
    private LocalTime timeIn;
    private LocalTime lunchStartTime;
    private LocalTime lunchEndTime;
    private String note;
    private String attendanceStatus;
    private double hoursWorked;
    private Long userId;
     UserSlimDTO userSlimDTO;



}
