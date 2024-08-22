package com.te.bookmydoctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.bookmydoctor.model.LeaveStatus;

public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Integer> {
    Optional<LeaveStatus> findByStatusCode(String statusCode);
}

