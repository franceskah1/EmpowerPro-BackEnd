package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.LeaveBalanceDTO;
import com.example.empowerprobackend.models.LeaveBalance;
import com.example.empowerprobackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeaveBalanceDTOtoLeaveBalance implements Converter<LeaveBalanceDTO, LeaveBalance> {
    @Override
    public LeaveBalance convert(LeaveBalanceDTO source) {
        if (source!=null){
            LeaveBalance leaveBalance=new LeaveBalance();
            leaveBalance.setId(source.getId());
            leaveBalance.setTotalDays(source.getTotalDay());
            leaveBalance.setSpentDays(0);
            leaveBalance.setRemainDays(source.getTotalDay());
             User user=new User();
            user.setId(source.getUserId());
            leaveBalance.setUser(user);
            return leaveBalance;
        }
        return null;
    }
}
