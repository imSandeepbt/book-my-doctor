package com.te.bookmydoctor.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.bookmydoctor.model.Doctor;
import com.te.bookmydoctor.model.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    boolean existsByDoctorAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStatus(
            Doctor doctor, LocalDate fromDate, LocalDate toDate, String status);
}
