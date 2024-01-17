package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.AttendanceDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.Attendance;
import com.example.empowerprobackend.models.NotificationTypes;
import com.example.empowerprobackend.models.Notifications;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.NotificationRepository;
import com.example.empowerprobackend.repositories.UserRepository;
import com.example.empowerprobackend.utils.AttendanceStatusUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;


@AllArgsConstructor
@Component
public class AttendanceDTOtoAttendance implements Converter<AttendanceDTO,Attendance> {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Attendance convert(AttendanceDTO source) {
        Attendance attendance = null;
        if (source != null) {
            attendance = new Attendance();
            attendance.setId(source.getId());
            attendance.setAttendanceDate(source.getAttendanceDate());
            attendance.setNote(source.getNote());
            attendance.setTimeIn(source.getTimeIn());
            attendance.setTimeOut(source.getTimeOut());
            attendance.setLunchEndTime(source.getLunchEndTime());
            attendance.setLunchStartTime(source.getLunchStartTime());
            User user=new User();
            user.setId(source.getUserId());
            attendance.setUser(user);
            attendance.setAttendanceStatus(AttendanceStatusUtils.getAttendance(source.getAttendanceStatus()));
            attendance.calculateHoursWorked();
            if (source.getTimeIn() != null && source.getTimeIn().isAfter(LocalTime.of(9, 0))) {
                notifyAdmin("User with id: " + source.getUserId() + " has time in after 9:00.", source.getUserId());
            }

            if (source.getTimeOut() != null && source.getTimeOut().isBefore(LocalTime.of(18, 0))) {
                notifyAdmin("User with id: " + source.getUserId() + " has time out before 18:00.", source.getUserId());
            }
        }
        return attendance;
    }


    private void notifyAdmin(String notifications,Long userId) {
        Notifications notifications1 = new Notifications();
        notifications1.setNotificationTime(LocalDateTime.now());
        notifications1.setNotificationTypes(NotificationTypes.Announcement);
        notifications1.setMessage(notifications);
        notifications1.setUser(userRepository.findById(userId).get());
        notificationRepository.save(notifications1);


    }

}