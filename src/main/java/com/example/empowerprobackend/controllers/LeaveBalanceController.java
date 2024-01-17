package com.example.empowerprobackend.controllers;
import com.example.empowerprobackend.dto.LeaveBalanceDTO;
import com.example.empowerprobackend.services.LeaveBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/EmpowerPro/leaveBalance")
@AllArgsConstructor
public class LeaveBalanceController {
    private final LeaveBalanceService leaveBalanceService;

    @PostMapping
    public ResponseEntity<?> saveUpdateBalance(@RequestBody LeaveBalanceDTO leaveBalanceDTO) {
        try {
            return new ResponseEntity<>(leaveBalanceService.saveOrUpdateLeaveRequest(leaveBalanceDTO), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/approve/{leaveRequestId}")
    public ResponseEntity<String> approveLeaveRequest(@PathVariable Long leaveRequestId) {
        try {
            leaveBalanceService.updateTotalDaysAndApproveLeaveRequest(leaveRequestId);
            return ResponseEntity.ok("Leave request approved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error approving leave request: " + e.getMessage());
        }
    }
        @PutMapping("/reject/{leaveRequestId}")
        public ResponseEntity<String> rejectLeaveRequest(@PathVariable Long leaveRequestId) {
            try {
                leaveBalanceService.rejectLeaveRequest(leaveRequestId);
                return ResponseEntity.ok("Leave request reject successfully");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error rejecting leave request: " + e.getMessage());
            }
        }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getLeaveBalanceById( @PathVariable String userId){
        try {
        return new ResponseEntity<>(leaveBalanceService.findLeaveBalanceByUserId(userId),HttpStatus.OK);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
     public ResponseEntity<?>findAllLeaveBalance(){
        try {
            return new ResponseEntity<>(leaveBalanceService.findAll(),HttpStatus.OK);
        }catch (NumberFormatException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteLeaveBalanceById(@PathVariable String id){
        try {
        return new ResponseEntity<>(leaveBalanceService.deleteBalanceById(id),HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (InternalError error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}


