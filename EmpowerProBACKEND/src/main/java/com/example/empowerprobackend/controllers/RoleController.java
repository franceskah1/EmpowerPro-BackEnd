package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.models.Role;
import com.example.empowerprobackend.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;

@RestController
@RequestMapping("/EmpowerPro/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?>saveRole(@RequestBody Role role){
        try {
            return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.OK);
        }catch (UnexpectedException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }catch (InternalError error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?>getAllRoles(){
        try {
            return new ResponseEntity<>(roleService.findAll(),HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (InternalError error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteRoleById(@PathVariable String id){
        try {
            return new ResponseEntity<>(roleService.deleteRoleById(id),HttpStatus.OK);
        }catch (EmptyResultDataAccessException dataAccessException){
            return new ResponseEntity<>(dataAccessException.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }catch (UnexpectedException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
