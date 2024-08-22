package com.te.bookmydoctor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;
import com.te.bookmydoctor.model.LeaveRequest;
import com.te.bookmydoctor.model.LeaveStatus;
import com.te.bookmydoctor.repository.DoctorRepository;
import com.te.bookmydoctor.repository.LeaveRequestRepository;
import com.te.bookmydoctor.repository.LeaveStatusRepository;
import com.te.bookmydoctor.service.AdminService;
import com.te.bookmydoctor.util.ConversionUtil;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    @Autowired
    private LeaveStatusRepository leaveStatusRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> ConversionUtil.convertToDto(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(leaveRequest -> ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class))
                .collect(Collectors.toList());
    }

    

    @Override
    public LeaveRequestDTO updateLeaveStatus(int leaveId, String statusCode) {
        // Find LeaveRequest by leaveId
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave Request not found"));

        // Find LeaveStatus by statusCode
        LeaveStatus leaveStatus = leaveStatusRepository.findByStatusCode(statusCode)
                .orElseThrow(() -> new RuntimeException("Invalid status code"));

        // Update the status and save
        leaveRequest.setStatus(leaveStatus.getStatusCode());
        leaveRequestRepository.save(leaveRequest);

        // Convert to DTO and return
        return ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class);
    }

}
