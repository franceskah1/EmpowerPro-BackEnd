package com.example.empowerprobackend.repositories;

import com.example.empowerprobackend.models.LeaveRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequests,Long> {

}
