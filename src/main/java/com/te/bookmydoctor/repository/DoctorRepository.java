package com.te.bookmydoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.bookmydoctor.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByNameContainingOrSpecialistInContaining(String name, String specialistIn);

	Doctor findByEmail(String email);
}
