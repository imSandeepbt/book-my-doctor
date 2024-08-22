package com.te.bookmydoctor.service;

import java.util.List;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
   
    DoctorDTO getDoctorById(int id);
	List<AppointmentDTO> getDoctorAppointments(int doctorId);

	LeaveRequestDTO applyForLeave(int doctorId, LeaveRequestDTO leaveRequestDTO);
}
