package com.example.empowerprobackend.utils;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.LeaveTypes;

public class LeaveTypesUtils {
    public static LeaveTypes getLeaveType(String leaveTypes){
        if (leaveTypes.equalsIgnoreCase(LeaveTypes.Vacation.name())){
            return LeaveTypes.Vacation;
        }else if (leaveTypes.equalsIgnoreCase(LeaveTypes.Other.name())){
            return LeaveTypes.Other;

        } else if (leaveTypes.equalsIgnoreCase(LeaveTypes.Sick.name())) {
            return LeaveTypes.Sick;
        }
            else {
                throw new NotFoundException("This type is not valid !Try another type from list.");
        }
        }
    }
