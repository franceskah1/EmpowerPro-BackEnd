package com.example.empowerprobackend.models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalance extends BaseEntity {

    private int totalDays;
    private int spentDays;
    private int remainDays;

    @ManyToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;


}




