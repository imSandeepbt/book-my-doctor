package com.te.bookmydoctor.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.bookmydoctor.model.Appointment;
import com.te.bookmydoctor.model.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByUserId(int userId);
    List<Appointment> findByDoctorId(int doctorId);
    boolean existsByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);}
