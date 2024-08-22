package com.te.bookmydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;
import com.te.bookmydoctor.dto.ResponseStructureDto;
import com.te.bookmydoctor.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/doctors")
	public ResponseEntity<ResponseStructureDto> getAllDoctors() {
		List<DoctorDTO> doctors = adminService.getAllDoctors();
		return ResponseEntity.ok(new ResponseStructureDto(false, "Doctors fetched successfully", doctors));
	}

	@GetMapping("/leaves")
	public ResponseEntity<ResponseStructureDto> getAllLeaveRequests() {
		List<LeaveRequestDTO> leaveRequests = adminService.getAllLeaveRequests();
		return ResponseEntity.ok(new ResponseStructureDto(false, "Leave requests fetched successfully", leaveRequests));
	}

	@PutMapping("/leave/{leaveId}/status")
	public ResponseEntity<LeaveRequestDTO> updateLeaveStatus(@PathVariable int leaveId,
			@RequestParam String statusCode) {

		LeaveRequestDTO updatedLeaveRequest = adminService.updateLeaveStatus(leaveId, statusCode);
		return ResponseEntity.ok(updatedLeaveRequest);
	}
}
