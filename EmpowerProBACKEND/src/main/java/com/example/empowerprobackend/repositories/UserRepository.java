package com.example.empowerprobackend.repositories;
import com.example.empowerprobackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Dictionary;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUsersByEmail(String email);

    User findUserByConfirmationToken(String token);

    @Query(value = "select * from user u where u.email=:email AND u.is_enabled=1", nativeQuery = true)
    Optional<User> findUsersByEmailEnabled(String email);

    User findByForgetPasswordToken(String uuid);
}