package com.example.empowerprobackend.converters;

import com.example.empowerprobackend.dto.LeaveRequestsDTO;
import com.example.empowerprobackend.models.LeaveRequests;
import com.example.empowerprobackend.models.LeaveStatus;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.utils.LeaveStatusUtils;
import com.example.empowerprobackend.utils.LeaveTypesUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestsDTOtoLeaveRequest implements Converter<LeaveRequestsDTO, LeaveRequests> {
    @Override
    public LeaveRequests convert(LeaveRequestsDTO source) {
        if (source!=null){
            LeaveRequests leaveRequests=new LeaveRequests();
           leaveRequests.setId(source.getId());
            leaveRequests.setStartDate(source.getStartDate());
            leaveRequests.setEndDate(source.getEndDate());
            leaveRequests.setNotes(source.getNotes());
            leaveRequests.setDuration(source.getDuration());
            leaveRequests.setLeaveTypes(LeaveTypesUtils.getLeaveType(source.getLeaveTypes()));
            leaveRequests.setLeaveStatus(LeaveStatus.Pending);
            User user = new User();
            user.setId(source.getUserId());
            leaveRequests.setUser(user);
            return leaveRequests;

        }
        return null;
    }
}
