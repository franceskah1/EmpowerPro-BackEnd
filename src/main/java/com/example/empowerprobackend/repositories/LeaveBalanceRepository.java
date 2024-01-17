package com.example.empowerprobackend.repositories;

import com.example.empowerprobackend.models.LeaveBalance;
import com.example.empowerprobackend.models.User;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
    Optional<LeaveBalance> findByUser(User user);


    Optional<LeaveBalance> findByUserId(Long userId);
}
