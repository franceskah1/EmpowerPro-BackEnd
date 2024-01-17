package com.example.empowerprobackend.utils;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.AttendanceStatus;

public class AttendanceStatusUtils {
    public static AttendanceStatus getAttendance(String status){
        if (status.equalsIgnoreCase(AttendanceStatus.Absent.name())){
            return AttendanceStatus.Absent;
        }
       else if (status.equalsIgnoreCase(AttendanceStatus.HalfDay.name())){
            return AttendanceStatus.HalfDay;
        }
       else if (status.equalsIgnoreCase(AttendanceStatus.Present.name())){
            return AttendanceStatus.Present;
        }
       else {
           throw new NotFoundException("This status is not valid.Select a type from list.");
        }
    }

}
