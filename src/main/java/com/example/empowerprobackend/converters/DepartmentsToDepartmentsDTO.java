package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.DepartmentsDTO;
import com.example.empowerprobackend.models.Departments;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@AllArgsConstructor
@Component
public class DepartmentsToDepartmentsDTO implements Converter<Departments, DepartmentsDTO> {

    private final UserToUserSlimDTO userSlimDTO;

    @Override
    public DepartmentsDTO convert(Departments source) {
      if (source!=null){
          DepartmentsDTO departmentsDTO=new DepartmentsDTO();
          departmentsDTO.setId(source.getId());
          departmentsDTO.setDepartmentName(source.getDepartmentName());
          departmentsDTO.setUsers(source.getUsers().stream().map(user -> userSlimDTO.convert(user)).collect(Collectors.toList()));
          return departmentsDTO;
      }
        return null;
    }
}
