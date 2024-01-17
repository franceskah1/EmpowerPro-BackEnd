package com.example.empowerprobackend.converters;
import com.example.empowerprobackend.dto.AttendanceDTO;
import com.example.empowerprobackend.models.Attendance;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class AttendanceToAttendanceDTO implements Converter<Attendance, AttendanceDTO> {
    private final UserToUserSlimDTO userSlimDTO;
    @Override
    public AttendanceDTO convert(Attendance source) {
    if (source!=null){
        AttendanceDTO attendanceDTO=new AttendanceDTO();
        attendanceDTO.setId(source.getId());
        attendanceDTO.setLunchStartTime(source.getLunchStartTime());
        attendanceDTO.setLunchEndTime(source.getLunchEndTime());
        attendanceDTO.setAttendanceDate(source.getAttendanceDate());
        attendanceDTO.setNote(source.getNote());
        attendanceDTO.setTimeIn(source.getTimeIn());
        attendanceDTO.setTimeOut(source.getTimeOut());
        attendanceDTO.setHoursWorked(source.getHoursWorked());
        attendanceDTO.setAttendanceStatus(source.getAttendanceStatus().name());
        attendanceDTO.setUserId(source.getUser().getId());
        attendanceDTO.setUserSlimDTO(userSlimDTO.convert(source.getUser()));
        return attendanceDTO;
    }
        return null;
    }
}
