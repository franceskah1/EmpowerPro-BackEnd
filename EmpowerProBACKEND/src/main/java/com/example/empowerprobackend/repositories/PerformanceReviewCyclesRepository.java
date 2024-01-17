package com.example.empowerprobackend.repositories;

import com.example.empowerprobackend.models.PerformanceReviewCycles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewCyclesRepository extends JpaRepository<PerformanceReviewCycles,Long> {
}
