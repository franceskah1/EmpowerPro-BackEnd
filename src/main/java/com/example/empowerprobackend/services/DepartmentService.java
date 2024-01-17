package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.DepartmentsDTOtoDepartments;
import com.example.empowerprobackend.converters.DepartmentsToDepartmentsDTO;
import com.example.empowerprobackend.dto.DepartmentsDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.repositories.DepartmentsRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentsRepository departmentsRepository;
    private final DepartmentsToDepartmentsDTO toDepartmentsDTO;
    private final DepartmentsDTOtoDepartments otoDepartments;
    private static final Logger logger = LogManager.getLogger(DepartmentService.class);



    public String saveOrUpdateDepartments(DepartmentsDTO departmentsDTO) {
        try {
            departmentsRepository.save(otoDepartments.convert(departmentsDTO));
            return "Department  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new department  could not be saved due to data access problems");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
    public DepartmentsDTO findById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Department id: \"" + id + "\" can't be parsed!");
        }
        return toDepartmentsDTO.convert(departmentsRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }


    public String deleteDepartmentById(String id) {

        try {
            Long parseId = Long.parseLong(id);
            departmentsRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {
      throw new InternalError("Department  id: \"" +id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("Department with id " + id + " not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
  }
    public List<DepartmentsDTO>findAll(){
        return departmentsRepository.findAll().stream().map(toDepartmentsDTO::convert).collect(Collectors.toList());
    }
}
