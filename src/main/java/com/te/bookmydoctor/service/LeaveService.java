package com.te.bookmydoctor.service;

import java.util.List;
import com.te.bookmydoctor.dto.LeaveRequestDTO;

public interface LeaveService {
    LeaveRequestDTO applyForLeave(int doctorId, LeaveRequestDTO leaveRequestDTO);
    List<LeaveRequestDTO> getAllLeaveRequests();
    LeaveRequestDTO approveLeave(int leaveId);
    LeaveRequestDTO rejectLeave(int leaveId);
}
