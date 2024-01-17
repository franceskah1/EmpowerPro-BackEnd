package com.example.empowerprobackend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseEntity {
    private LocalDate attendanceDate;
    private LocalTime timeOut;
    private LocalTime timeIn;
    private LocalTime lunchStartTime;
    private LocalTime lunchEndTime;
    private String note;
    private double hoursWorked ;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


//one employee can have multiple attendance records
//presenting different days or instances of attendance
public void calculateHoursWorked() {
    if (timeIn != null && timeOut != null) {
        // Calculate total hours worked excluding lunch break
        Duration workDuration = Duration.between(timeIn, timeOut);

        // Subtract lunch break duration if applicable
        if (this.lunchStartTime != null && this.lunchEndTime != null) {
            Duration lunchBreak = Duration.between(this.lunchStartTime, this.lunchEndTime);
            workDuration = workDuration.minus(lunchBreak);
        }

        // Convert duration to hours
        this.hoursWorked = workDuration.toHours();
    } else {
        // Handle the case where either timeIn or timeOut is null
        // You may want to log a warning or handle it according to your requirements
        this.hoursWorked = 0;
    }
}

}
