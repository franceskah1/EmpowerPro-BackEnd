package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.LeaveRequestToLeaveRequestDTO;
import com.example.empowerprobackend.converters.LeaveRequestsDTOtoLeaveRequest;
import com.example.empowerprobackend.dto.LeaveRequestsDTO;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.NotificationTypes;
import com.example.empowerprobackend.models.Notifications;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.LeaveRequestRepository;
import com.example.empowerprobackend.repositories.NotificationRepository;
import com.example.empowerprobackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestToLeaveRequestDTO leaveRequestDTO;
    private final LeaveRequestsDTOtoLeaveRequest request;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private static final Logger logger = LogManager.getLogger(AttendanceService.class);

    public String saveOrUpdateLeaveRequest(LeaveRequestsDTO leaveRequestsDTO) {
        try {
            leaveRequestRepository.save(Objects.requireNonNull(request.convert(leaveRequestsDTO)));
            createLeaveRequestNotification(leaveRequestsDTO);
            return "Leave Request  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new record  could not be saved due to data access problems");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }
    private void createLeaveRequestNotification(LeaveRequestsDTO leaveRequestsDTO) {
        try {
            Notifications notification = new Notifications();
            notification.setNotificationTypes(NotificationTypes.LeaveRequest);
            notification.setMessage("Employee with id:" + leaveRequestsDTO.getUserId()+ " has submitted a leave request.");
            notification.setNotificationTime(LocalDateTime.now());

            User user = userRepository.findById(leaveRequestsDTO.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

                 notification.setUser(user);

            notificationRepository.save(notification);
        } catch (Exception e) {
            logger.error("Error creating notification: " + e.getMessage(), e);
        }

    }


    public LeaveRequestsDTO findById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("LeaveRequest id: \"" + id + "\" can't be parsed!");
        }
        return leaveRequestDTO.convert(leaveRequestRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }

    public List<LeaveRequestsDTO> findAll(){
        return leaveRequestRepository.findAll().stream().map(leaveRequests -> leaveRequestDTO.convert(leaveRequests)).collect(Collectors.toList());
    }

    public String deleteById(String id) {
        try {
            Long parseId = Long.parseLong(id);
            leaveRequestRepository.deleteById(parseId);
            return "Record deleted successfully";
        } catch (NumberFormatException e) {

            throw new InternalError("LeaveRequest  id: \"" + id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new InternalError("LeaveRequest with id " + id + " not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }





}

