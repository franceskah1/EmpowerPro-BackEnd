package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.LeaveRequestsDTO;
import com.example.empowerprobackend.models.LeaveRequests;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestToLeaveRequestDTO implements Converter<LeaveRequests, LeaveRequestsDTO> {
    @Override
    public LeaveRequestsDTO convert(LeaveRequests source) {
        if (source != null) {
            LeaveRequestsDTO leaveRequestsDTO = new LeaveRequestsDTO();
            leaveRequestsDTO.setId(source.getId());
            leaveRequestsDTO.setStartDate(source.getStartDate());
            leaveRequestsDTO.setEndDate(source.getEndDate());
            leaveRequestsDTO.setDuration(source.getDuration());
            leaveRequestsDTO.setLeaveTypes(source.getLeaveTypes().name());
            leaveRequestsDTO.setUserId(source.getUser().getId());
            leaveRequestsDTO.setNotes(source.getNotes());
            leaveRequestsDTO.setLeaveStatus(source.getLeaveStatus().name());
            return leaveRequestsDTO;
        }
        return null;
    }
}