package com.te.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.bookmydoctor.dto.AdminDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LoginDTO;
import com.te.bookmydoctor.dto.ResponseStructureDto;
import com.te.bookmydoctor.dto.UserDTO;
import com.te.bookmydoctor.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<ResponseStructureDto> login(@Valid @RequestBody LoginDTO loginDTO) {
		return ResponseEntity
				.ok(new ResponseStructureDto(false, "Login successful", authenticationService.login(loginDTO)));
	}

	@PostMapping("/register/admin")
	public ResponseEntity<ResponseStructureDto> registerAdmin(@Valid @RequestBody AdminDTO adminDTO) {
		return ResponseEntity.ok(new ResponseStructureDto(false, "Admin Registration Successful",
				authenticationService.registerAdmin(adminDTO)));
	}

	@PostMapping("/register/user")
	public ResponseEntity<ResponseStructureDto> registerUser(@Valid @RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(new ResponseStructureDto(false, "User Registration Successful",
				authenticationService.registerUser(userDTO)));
	}

	@PostMapping("/register/doctor")
	public ResponseEntity<ResponseStructureDto> registerDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
		return ResponseEntity.ok(new ResponseStructureDto(false, "Doctor Registration Successful",
				authenticationService.registerDoctor(doctorDTO)));
	}
}
