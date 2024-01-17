package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.exceptions.NoAttendanceRecordException;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.services.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EmpowerPro/attendance-report")
@AllArgsConstructor
public class AttendanceReportController {
    private final AttendanceService attendanceService;

    @GetMapping("/generate/{userId}")
    public ResponseEntity<?> generateAttendanceReport(@PathVariable Long userId) {
        try {
            byte[] report = attendanceService.generateAttendanceReport(userId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "attendance_report.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (NoAttendanceRecordException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No attendance records found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating report.");
        }
    }
}



