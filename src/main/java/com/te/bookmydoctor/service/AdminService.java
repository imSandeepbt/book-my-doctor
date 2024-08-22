package com.te.bookmydoctor.service;

import java.util.List;

import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;

public interface AdminService {
    List<DoctorDTO> getAllDoctors();
    List<LeaveRequestDTO> getAllLeaveRequests();

	LeaveRequestDTO updateLeaveStatus(int leaveId, String statusCode);
}
