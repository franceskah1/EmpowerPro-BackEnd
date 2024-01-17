package com.example.empowerprobackend.services;
import com.example.empowerprobackend.converters.AttendanceDTOtoAttendance;
import com.example.empowerprobackend.converters.AttendanceToAttendanceDTO;
import com.example.empowerprobackend.dto.AttendanceDTO;
import com.example.empowerprobackend.exceptions.NoAttendanceRecordException;
import com.example.empowerprobackend.exceptions.NotFoundException;
import com.example.empowerprobackend.models.Attendance;
import com.example.empowerprobackend.models.User;
import com.example.empowerprobackend.repositories.AttendanceRepository;
import com.example.empowerprobackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private AttendanceDTOtoAttendance dtOtoAttendance;
    private UserRepository userRepository;
    private AttendanceToAttendanceDTO attendanceDTO;

    private static final Logger logger = LogManager.getLogger(AttendanceService.class);

    public String saveOrUpdateAttendance(AttendanceDTO attendanceDTO) {
        try {
           attendanceRepository.save(Objects.requireNonNull(dtOtoAttendance.convert(attendanceDTO)));
            return "Attendance  Saved Successfully.";
        } catch (DataAccessException e) {
            throw new InternalError("The new record  could not be saved due to data access problems");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }

    }
    public AttendanceDTO findById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Attendance id: \"" + id + "\" can't be parsed!");
        }
        return attendanceDTO.convert(attendanceRepository.findById(parseId).orElseThrow(() -> new NotFoundException("Record with id: " + id + " not found!")));
    }

    public List<AttendanceDTO>findAll(){
        return attendanceRepository.findAll().stream().map(attendance -> attendanceDTO.convert(attendance)).collect(Collectors.toList());
    }

public String deleteById(String id) {
    try {
        Long parseId = Long.parseLong(id);
        attendanceRepository.deleteById(parseId);
        return "Record deleted successfully";
    } catch (NumberFormatException e) {

        throw new InternalError("Attendance  id: \"" + id + "\" can't be parsed!");
    } catch (EmptyResultDataAccessException e) {
        throw new InternalError("Attendance with id " + id + " not found");
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
    }
}

    public List <AttendanceDTO> findByAttendanceDate(LocalDate attendanceDate) {
try{
       return attendanceRepository.findByAttendanceDate(attendanceDate).stream().map(attendance -> attendanceDTO.convert(attendance)).collect(Collectors.toList());
    }
catch (EmptyResultDataAccessException e) {
    throw new InternalError("Attendance with attendanceDate " + attendanceDate + " not found");
} catch (Exception e) {
    logger.error(e.getMessage(), e);
    throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
}
    }


    public byte[] generateAttendanceReport(Long userId) throws NotFoundException, NoAttendanceRecordException, IOException {
        User user = userRepository.findById(userId).get();
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByUserId(userId);

        if (attendanceList.isEmpty()) {
            throw new NoAttendanceRecordException();
        }
        return AttendanceReportGenerator.generatePdfReport(user, attendanceList);
    }
    public List<AttendanceDTO>findByRangeDate(LocalDate startDate,LocalDate endDate){
        try {
            List<Attendance>attendanceList=attendanceRepository.findByDateRange(startDate, endDate);
            return attendanceList.stream().map(attendance -> attendanceDTO.convert(attendance)).collect(Collectors.toList());
        }catch (EmptyResultDataAccessException e){
            throw new InternalError("Attendance within the date range not found");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new InternalError("An error occurred while processing your request. Please try again later. Thank you for your understanding.");
        }
    }

}


