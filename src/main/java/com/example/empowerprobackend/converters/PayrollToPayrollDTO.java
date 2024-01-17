package com.example.empowerprobackend.converters;

import com.example.empowerprobackend.dto.PayrollDTO;
import com.example.empowerprobackend.models.Payroll;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PayrollToPayrollDTO implements Converter<Payroll, PayrollDTO> {
    @Override
    public PayrollDTO convert(Payroll source) {
        if (source!=null){
            PayrollDTO payrollDTO=new PayrollDTO();
            payrollDTO.setPayrollDate(source.getPayrollDate());
            payrollDTO.setId(source.getId());
            payrollDTO.setUserId(source.getUser().getId());
            payrollDTO.setBonus(source.getBonus());
            payrollDTO.setSalary(source.getSalary());
           // payrollDTO.setTotalSalary(source.getTotalSalary());

            return  payrollDTO;
        }
        return null;
    }
}

