package com.example.empowerprobackend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private NotificationTypes notificationTypes;
    private String message;
    private LocalDateTime notificationTime;


    @ManyToOne
    @JoinColumn(name="user_Id",referencedColumnName = "id")
    private User user;


}
