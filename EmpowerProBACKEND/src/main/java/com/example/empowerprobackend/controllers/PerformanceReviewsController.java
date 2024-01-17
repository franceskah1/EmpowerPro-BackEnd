package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.PerformanceReviewsDTO;
import com.example.empowerprobackend.services.PerformanceReviewsService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/EmpowerPro/PerformanceReview")
public class PerformanceReviewsController {
    private final PerformanceReviewsService performanceReviewsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(performanceReviewsService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformanceReviewById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(performanceReviewsService.deleteById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllPerformanceReviewS() {
        try {
            return new ResponseEntity<>(performanceReviewsService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<?> saveUpdatePerformanceReview(@RequestBody PerformanceReviewsDTO performanceReviewsDTO) {
        try {
            return new ResponseEntity<>(performanceReviewsService.saveOrUpdatePerformanceReview(performanceReviewsDTO), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
