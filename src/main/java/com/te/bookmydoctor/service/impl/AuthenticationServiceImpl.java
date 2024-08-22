package com.te.bookmydoctor.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.bookmydoctor.dto.AdminDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LoginDTO;
import com.te.bookmydoctor.dto.UserDTO;
import com.te.bookmydoctor.model.Admin;
import com.te.bookmydoctor.model.Doctor;
import com.te.bookmydoctor.model.User;
import com.te.bookmydoctor.repository.AdminRepository;
import com.te.bookmydoctor.repository.DoctorRepository;
import com.te.bookmydoctor.repository.UserRepository;
import com.te.bookmydoctor.service.AuthenticationService;
import com.te.bookmydoctor.util.ConversionUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Object login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        Doctor doctor = doctorRepository.findByEmail(loginDTO.getEmail());
        Admin admin = adminRepository.findByEmail(loginDTO.getEmail());

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            return generateLoginResponse(user.getEmail(), "USER");
        } else if (doctor != null && doctor.getPassword().equals(loginDTO.getPassword())) {
            return generateLoginResponse(doctor.getEmail(), "DOCTOR");
        } else if (admin != null && admin.getPassword().equals(loginDTO.getPassword())) {
            return generateLoginResponse(admin.getEmail(), "ADMIN");
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private Map<String, Object> generateLoginResponse(String email, String role) {
        String token = generateToken(email, role);
        Map<String, Object> response = new HashMap<>();
        response.put("role", role);
        response.put("token", token);
        return response;
    }

    @Override
    public AdminDTO registerAdmin(AdminDTO adminDTO) {
        if (adminRepository.count() > 0) {
            throw new RuntimeException("Only one admin is allowed.");
        }
        Admin admin = ConversionUtil.convertToEntity(adminDTO, Admin.class);
        Admin savedAdmin = adminRepository.save(admin);
        AdminDTO responseDTO = ConversionUtil.convertToDto(savedAdmin, AdminDTO.class);
        responseDTO.setPassword(null);
        return responseDTO;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = ConversionUtil.convertToEntity(userDTO, User.class);
        User savedUser = userRepository.save(user);
        UserDTO responseDTO = ConversionUtil.convertToDto(savedUser, UserDTO.class);
        responseDTO.setPassword(null);
        return responseDTO;
    }

    @Override
    public DoctorDTO registerDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = ConversionUtil.convertToEntity(doctorDTO, Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return ConversionUtil.convertToDto(savedDoctor, DoctorDTO.class);
    }

    private String generateToken(String email, String role) {
        String data = email + ":" + role + ":" + UUID.randomUUID().toString();
        String hashedData = hashData(data);
        return Base64.getEncoder().encodeToString(hashedData.getBytes(StandardCharsets.UTF_8));
    }

    private String hashData(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return new String(hash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
