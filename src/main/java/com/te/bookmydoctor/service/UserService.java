package com.te.bookmydoctor.service;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.DoctorDTO;

import java.util.List;

public interface UserService {

    List<DoctorDTO> searchDoctors(String query);

    DoctorDTO getDoctorById(int doctorId);

    AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> getAppointments(int userId);
}
