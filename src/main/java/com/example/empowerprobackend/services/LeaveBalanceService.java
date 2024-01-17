package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.LeaveBalanceDTOtoLeaveBalance;
import com.example.empowerprobackend.converters.LeaveBalanceToLeaveBalanceDTO;
import com.example.empowerprobackend.dto.LeaveBalanceDTO;
import com.example.empowerprobackend.dto.LeaveRequestsDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.*;
import com.example.empowerprobackend.repositories.LeaveBalanceRepository;
import com.example.empowerprobackend.repositories.LeaveRequestRepository;
import com.example.empowerprobackend.repositories.NotificationRepository;
import com.example.empowerprobackend.repositories.UserRepository;
import com.sun.jdi.InternalException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LeaveBalanceService {
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveBalanceDTOtoLeaveBalance leaveBalance;
    private final LeaveRequestRepository leaveRequestsRepository;
    private final LeaveBalanceToLeaveBalanceDTO leaveBalanceDTO;
    private final NotificationRepository notificationRepository;

    private static final Logger logger = LogManager.getLogger(LeaveBalanceService.class);


    public String saveOrUpdateLeaveRequest(LeaveBalanceDTO leaveBalanceDTO) {
        try {
            leaveBalanceRepository.save(Objects.requireNonNull(leaveBalance.convert(leaveBalanceDTO)));
            return "Leave Request  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new record  could not be saved due to data access problems");
        } catch (Exception e) {

            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
    public void updateTotalDaysAndApproveLeaveRequest(Long leaveRequestId) {
        LeaveRequests leaveRequest = leaveRequestsRepository.findById(leaveRequestId)
                .orElseThrow(() -> new NoSuchElementException("Leave request not found"));

        if (leaveRequest.getLeaveStatus() == LeaveStatus.Pending) {

            LeaveBalance leaveBalance = leaveBalanceRepository.findByUser(leaveRequest.getUser())
                    .orElseThrow(() -> new NoSuchElementException("Leave balance not found for user"));
            int remainingDays = leaveBalance.getRemainDays() - leaveRequest.getDuration();
            leaveBalance.setRemainDays(remainingDays);
            leaveBalance.setSpentDays(leaveBalance.getTotalDays() - remainingDays);

            leaveRequest.setLeaveStatus(LeaveStatus.Approved);
            leaveBalanceRepository.save(leaveBalance);
            leaveRequestsRepository.save(leaveRequest);
            sendLeaveApprovalNotification(leaveRequest.getUser(),"Your leave request has been approved");
        } else {
            throw new IllegalStateException("Leave request is not pending approval");
        }
    }
public void rejectLeaveRequest(Long leaveRequestId){
        LeaveRequests leaveRequests=leaveRequestsRepository.findById(leaveRequestId).orElseThrow(()->new NotFoundException( "Leave Request not found!"));
        if (leaveRequests.getLeaveStatus()==LeaveStatus.Pending){
            leaveRequests.setLeaveStatus(LeaveStatus.Rejected);
            leaveRequestsRepository.save(leaveRequests);
            sendLeaveApprovalNotification(leaveRequests.getUser(),"Your leave request has been rejected");
        } else if (leaveRequests.getLeaveStatus()==LeaveStatus.Approved) {
            throw new IllegalStateException("Cannot reject an already approved leave request");
        } else if (leaveRequests.getLeaveStatus() == LeaveStatus.Rejected) {
            throw new IllegalStateException("Leave request is already rejected");
        } else {
            throw new IllegalStateException("Invalid leave request status");


        }
}
    public List<LeaveBalanceDTO> findAll() {
        return leaveBalanceRepository.findAll().stream().map(leaveBalanceDTO::convert).collect(Collectors.toList());
    }

    public String deleteBalanceById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
            leaveBalanceRepository.deleteById(parseId);
            return "Leave Balance delete success!";
        } catch (NumberFormatException e) {
            throw new InternalException("This id:" + id + "can't be parsed!");

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Leave Balance with id :" + id + "not found!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }

    public LeaveBalanceDTO findLeaveBalanceById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("This id:" + id + "can't be parsed!");
        }
        return leaveBalanceDTO.convert(leaveBalanceRepository.findById(parseId).orElseThrow(() -> new NotFoundException("LeaveBalance with id :" + id + "not found!")));
    }
public LeaveBalanceDTO findLeaveBalanceByUserId(String userId){
    long parseId;
    try {
        parseId = Long.parseLong(userId);
    } catch (NumberFormatException exception) {
        throw new NumberFormatException("This id:" + userId + "can't be parsed!");
    }
    return leaveBalanceDTO.convert(leaveBalanceRepository.findByUserId(parseId).orElseThrow(() -> new NotFoundException("LeaveBalance with id :" + userId + "not found!")));
}

    private void sendLeaveApprovalNotification(User user, String message) {
        Notifications notification = new Notifications();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setNotificationTypes(NotificationTypes.LeaveRequest);
        notification.setNotificationTime(LocalDateTime.now());
        notificationRepository.save(notification);
    }



}





