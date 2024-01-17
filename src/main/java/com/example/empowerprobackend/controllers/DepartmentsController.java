package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.DepartmentsDTO;
import com.example.empowerprobackend.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/EmpowerPro/departments")
@AllArgsConstructor
public class DepartmentsController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> saveUpdateDepartments(@RequestBody DepartmentsDTO departmentsDTO) {
        try {
            return new ResponseEntity<>(departmentService.saveOrUpdateDepartments(departmentsDTO), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDepartmentById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(departmentService.deleteDepartmentById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?>findAllDepartments() {
        try {
            return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
