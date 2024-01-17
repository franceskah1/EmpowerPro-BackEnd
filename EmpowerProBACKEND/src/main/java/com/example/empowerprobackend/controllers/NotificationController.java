package com.example.empowerprobackend.controllers;

import com.example.empowerprobackend.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/EmpowerPro/notification")
@RestController
public class NotificationController {
    private NotificationService notificationService;



    @GetMapping
    public ResponseEntity<?>findAllNotifications() {
        try {
            return new ResponseEntity<>(notificationService.findAllNotifications(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } @GetMapping("/{id}")
    public ResponseEntity<?> findNotificationById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(notificationService.getNotificationById(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(notificationService.deleteById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
