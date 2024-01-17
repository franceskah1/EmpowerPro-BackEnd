package com.example.empowerprobackend.services;

import com.example.empowerprobackend.converters.NotificationToNotificationDTO;
import com.example.empowerprobackend.dto.NotificationDTO;
import com.example.empowerprobackend.dto.PayrollDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.Notifications;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationToNotificationDTO toNotificationDTO;


    public String deleteById(String id) {
        try {
            Long parseId = Long.parseLong(id);
            notificationRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {

            throw new InternalError("Notification  id: \"" + id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("Notification with id " + id + " not found");
        } catch (Exception e) {
            ;
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }
    public List<NotificationDTO>findAllNotifications(){
        return notificationRepository.findAll().stream().map(notifications -> toNotificationDTO.convert(notifications)).collect(Collectors.toList());


    }

    public NotificationDTO getNotificationById(String id){
        Long parseId;
        try {
            parseId=Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Notification id: \"" + id + "\" can't be parsed!");
        }
        return toNotificationDTO.convert(notificationRepository.findById(parseId).orElseThrow(()->new NotFoundException("Notification with id :"+ parseId + " not found!")));
    }

}
