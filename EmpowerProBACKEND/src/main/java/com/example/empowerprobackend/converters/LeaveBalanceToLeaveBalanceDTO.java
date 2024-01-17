package com.example.empowerprobackend.converters;

import com.example.empowerprobackend.dto.LeaveBalanceDTO;
import com.example.empowerprobackend.models.LeaveBalance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeaveBalanceToLeaveBalanceDTO implements Converter<LeaveBalance, LeaveBalanceDTO> {
    @Override
    public LeaveBalanceDTO convert(LeaveBalance source) {
       if (source!=null){
           LeaveBalanceDTO leaveBalanceDTO=new LeaveBalanceDTO();
           leaveBalanceDTO.setId(source.getId());
           leaveBalanceDTO.setUserId(source.getUser().getId());
           leaveBalanceDTO.setRemainDay(source.getRemainDays());
           leaveBalanceDTO.setSpentDay(source.getSpentDays());
           leaveBalanceDTO.setTotalDay(source.getTotalDays());
           return leaveBalanceDTO;
       }
        return null;
    }
}
