package com.example.empowerprobackend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequests extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private LeaveTypes leaveTypes;
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;

    private String notes;

    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;

    @ManyToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;



}