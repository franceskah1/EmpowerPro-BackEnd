package com.example.empowerprobackend.repositories;

import com.example.empowerprobackend.models.PerformanceReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewsRepository extends JpaRepository<PerformanceReviews,Long> {
}
