package com.te.bookmydoctor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;
import com.te.bookmydoctor.model.Doctor;
import com.te.bookmydoctor.model.LeaveRequest;
import com.te.bookmydoctor.repository.AppointmentRepository;
import com.te.bookmydoctor.repository.DoctorRepository;
import com.te.bookmydoctor.repository.LeaveRequestRepository;
import com.te.bookmydoctor.service.DoctorService;
import com.te.bookmydoctor.util.ConversionUtil;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
            .map(doctor -> ConversionUtil.convertToDto(doctor, DoctorDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorById(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        return ConversionUtil.convertToDto(doctor, DoctorDTO.class);
    }

    @Override
    public List<AppointmentDTO> getDoctorAppointments(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
            .map(appointment -> ConversionUtil.convertToDto(appointment, AppointmentDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDTO applyForLeave(int doctorId, LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = ConversionUtil.convertToEntity(leaveRequestDTO, LeaveRequest.class);
        leaveRequest.setDoctor(doctorRepository.findById(doctorId)
            .orElseThrow(() -> new RuntimeException("Doctor not found")));
        leaveRequestRepository.save(leaveRequest);
        return ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class);
    }
}
