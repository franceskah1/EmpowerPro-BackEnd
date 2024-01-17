package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.DepartmentsDTO;
import com.example.empowerprobackend.models.Departments;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentsDTOtoDepartments implements Converter<DepartmentsDTO, Departments> {
    @Override
    public Departments convert(DepartmentsDTO source) {
        if (source!=null){
            Departments departments=new Departments();
            departments.setId(source.getId());
            departments.setDepartmentName(source.getDepartmentName());
           return  departments;

        }
        return null;
    }
}
