package com.example.empowerprobackend.repositories;
import com.example.empowerprobackend.models.Attendance;
import com.example.empowerprobackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
 //   @Query(value = "select * from attendance a where a.attendanceDate= :attendanceDate", nativeQuery = true)
    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);


    List<Attendance>findAttendanceByUserId(Long userId);


    @Query("SELECT a FROM Attendance a WHERE a.attendanceDate BETWEEN :startDate AND :endDate")
    List<Attendance> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
