package com.example.empowerprobackend.utils;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.NotificationTypes;
public class NotificationTypesUtils {
    public static NotificationTypes getNotificationType(String notificationTypes){
        if (notificationTypes.equalsIgnoreCase(NotificationTypes.General.name())){
            return NotificationTypes.General;
        } else if (notificationTypes.equalsIgnoreCase(NotificationTypes.Announcement.name())) {
            return NotificationTypes.Announcement;
        } else if (notificationTypes.equalsIgnoreCase(NotificationTypes.LeaveRequest.name())) {
            return NotificationTypes.LeaveRequest;
    } else if (notificationTypes.equalsIgnoreCase(NotificationTypes.PerformanceReview.name())) {
        return NotificationTypes.PerformanceReview;
    }
      else {
                throw new NotFoundException("This type is not valid !Try another type from list.");
        }
    }

    }
