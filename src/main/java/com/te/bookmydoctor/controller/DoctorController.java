package com.te.bookmydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.LeaveRequestDTO;
import com.te.bookmydoctor.dto.ResponseStructureDto;
import com.te.bookmydoctor.service.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<ResponseStructureDto> getDoctorAppointments(@Valid @PathVariable int doctorId) {
        List<AppointmentDTO> appointments = doctorService.getDoctorAppointments(doctorId);
        return ResponseEntity.ok(new ResponseStructureDto(false, "Appointments fetched successfully", appointments));
    }

    @PostMapping("/{doctorId}/leave")
    public ResponseEntity<ResponseStructureDto> applyForLeave(@Valid @PathVariable int doctorId,
                                                              @RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveRequestDTO appliedLeave = doctorService.applyForLeave(doctorId, leaveRequestDTO);
        return ResponseEntity.ok(new ResponseStructureDto(false, "Leave applied successfully", appliedLeave));
    }
}
