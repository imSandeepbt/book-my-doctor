package com.te.bookmydoctor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.bookmydoctor.dto.LeaveRequestDTO;
import com.te.bookmydoctor.model.LeaveRequest;
import com.te.bookmydoctor.repository.LeaveRequestRepository;
import com.te.bookmydoctor.service.LeaveService;
import com.te.bookmydoctor.util.ConversionUtil;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequestDTO applyForLeave(int doctorId, LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = ConversionUtil.convertToEntity(leaveRequestDTO, LeaveRequest.class);
        leaveRequestRepository.save(leaveRequest);
        return leaveRequestDTO;
    }

    @Override
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
            .map(leaveRequest -> ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDTO approveLeave(int leaveId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave Request not found"));
        leaveRequest.setStatus("Approved");
        leaveRequestRepository.save(leaveRequest);
        return ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class);
    }

    @Override
    public LeaveRequestDTO rejectLeave(int leaveId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave Request not found"));
        leaveRequest.setStatus("Rejected");
        leaveRequestRepository.save(leaveRequest);
        return ConversionUtil.convertToDto(leaveRequest, LeaveRequestDTO.class);
    }
}
