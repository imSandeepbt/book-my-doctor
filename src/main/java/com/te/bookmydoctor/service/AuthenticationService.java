package com.te.bookmydoctor.service;

import com.te.bookmydoctor.dto.AdminDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.dto.LoginDTO;
import com.te.bookmydoctor.dto.UserDTO;

public interface AuthenticationService {
	Object login(LoginDTO loginDTO);
    UserDTO registerUser(UserDTO userDTO);
    DoctorDTO registerDoctor(DoctorDTO doctorDTO);
    AdminDTO registerAdmin(AdminDTO adminDTO);
}
