package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.PayrollDTO;
import com.example.empowerprobackend.models.Payroll;
import com.example.empowerprobackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class PayrollDTOtoPayroll implements Converter<PayrollDTO,Payroll> {
    @Override
    public Payroll convert(PayrollDTO source) {
        if (source!=null){
            Payroll payroll=new Payroll();
            payroll.setPayrollDate(source.getPayrollDate());
            payroll.setSalary(source.getSalary());
            payroll.setBonus(source.getBonus());
            payroll.setId(source.getId());
           //payroll.calculate
            User user = new User();
            user.setId(source.getUserId());
            payroll.setUser(user);
            return payroll;
        }

        return null;
    }
}
