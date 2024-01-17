package com.example.empowerprobackend.utils;

import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.LeaveStatus;
import com.example.empowerprobackend.models.LeaveTypes;

public class LeaveStatusUtils {
    public static LeaveStatus getLeaveStatus(String leaveStatus){
        if (leaveStatus.equalsIgnoreCase(LeaveStatus.Pending.name())){
            return LeaveStatus.Pending;
        }else if (leaveStatus.equalsIgnoreCase(LeaveStatus.Approved.name())){
            return LeaveStatus.Approved;

        } else if (leaveStatus.equalsIgnoreCase(LeaveStatus.Rejected.name())) {
            return LeaveStatus.Rejected;
        }
        else {
            throw new NotFoundException("This status is not valid !");
        }
    }
}

