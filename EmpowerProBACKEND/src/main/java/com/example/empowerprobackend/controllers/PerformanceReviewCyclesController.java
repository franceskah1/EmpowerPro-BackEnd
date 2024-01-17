package com.example.empowerprobackend.controllers;

import com.example.empowerprobackend.dto.DepartmentsDTO;
import com.example.empowerprobackend.dto.PerformanceReviewCyclesDTO;
import com.example.empowerprobackend.services.PerformanceReviewCyclesService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/EmpowerPro/PerformanceReviewCycles")
@AllArgsConstructor
public class PerformanceReviewCyclesController {
    private final PerformanceReviewCyclesService performanceReviewCyclesService;

    @PostMapping
    public ResponseEntity<?> saveUpdateDepartments(@RequestBody PerformanceReviewCyclesDTO performanceReviewCyclesDTO) {
        try {
            return new ResponseEntity<>(performanceReviewCyclesService.saveOrUpdatePerformanceReviewCycles(performanceReviewCyclesDTO), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPerformanceReviewCyclesById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(performanceReviewCyclesService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformanceReviewCyclesById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(performanceReviewCyclesService.deletePerformanceReviewCyclesById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?>findAllPerformanceReviewCycles() {
        try {
            return new ResponseEntity<>(performanceReviewCyclesService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
