package com.example.empowerprobackend.repositories;

import com.example.empowerprobackend.models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications,Long> {
}
