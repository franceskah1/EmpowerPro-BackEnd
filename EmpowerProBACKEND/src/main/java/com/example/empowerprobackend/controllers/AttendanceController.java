package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.AttendanceDTO;
import com.example.empowerprobackend.services.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/EmpowerPro/attendance")
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<?> saveUpdateAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            return new ResponseEntity<>(attendanceService.saveOrUpdateAttendance(attendanceDTO), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(attendanceService.findById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendanceById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(attendanceService.deleteById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllAttendance() {
        try {
            return new ResponseEntity<>(attendanceService.findAll(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getByAttendanceDate/{attendanceDate}")
    public ResponseEntity<?> findByAttendanceDate(@PathVariable LocalDate attendanceDate) {
        try {
            return new ResponseEntity<>(attendanceService.findByAttendanceDate(attendanceDate), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/findByDateRange")
    public ResponseEntity<?>findByRangeDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            return new ResponseEntity<>(attendanceService.findByRangeDate(startDate, endDate),HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
