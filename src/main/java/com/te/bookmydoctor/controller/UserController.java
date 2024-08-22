package com.te.bookmydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.ResponseStructureDto;
import com.te.bookmydoctor.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ResponseEntity<ResponseStructureDto> searchDoctors(@Valid @RequestParam String query) {
		List<DoctorDTO> doctors = userService.searchDoctors(query);
		return ResponseEntity.ok(new ResponseStructureDto(false, "Doctors retrieved successfully", doctors));
	}

	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<ResponseStructureDto> getDoctorById(@Valid @PathVariable int doctorId) {
		DoctorDTO doctor = userService.getDoctorById(doctorId);
		return ResponseEntity.ok(new ResponseStructureDto(false, "Doctor details retrieved successfully", doctor));
	}

	@PostMapping("/appointment")
	public ResponseEntity<ResponseStructureDto> bookAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) {
		try {
			AppointmentDTO bookedAppointment = userService.bookAppointment(appointmentDTO);
			return ResponseEntity
					.ok(new ResponseStructureDto(false, "Appointment booked successfully", bookedAppointment));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseStructureDto(true, e.getMessage(), null));
		}
	}

	@GetMapping("/appointments")
	public ResponseEntity<ResponseStructureDto> getAppointments(@Valid @RequestParam int userId) {
		List<AppointmentDTO> appointments = userService.getAppointments(userId);
		return ResponseEntity.ok(new ResponseStructureDto(false, "Appointments retrieved successfully", appointments));
	}
}
