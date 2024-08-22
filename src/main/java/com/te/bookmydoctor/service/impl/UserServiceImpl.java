package com.te.bookmydoctor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.bookmydoctor.dto.AppointmentDTO;
import com.te.bookmydoctor.dto.DoctorDTO;
import com.te.bookmydoctor.exception.DoctorAlreadyBookedException;
import com.te.bookmydoctor.exception.DoctorNotFoundException;
import com.te.bookmydoctor.exception.DoctorOnLeaveException;
import com.te.bookmydoctor.exception.UserNotFoundException;
import com.te.bookmydoctor.model.Appointment;
import com.te.bookmydoctor.model.Doctor;
import com.te.bookmydoctor.model.User;
import com.te.bookmydoctor.repository.AppointmentRepository;
import com.te.bookmydoctor.repository.DoctorRepository;
import com.te.bookmydoctor.repository.LeaveRequestRepository;
import com.te.bookmydoctor.repository.UserRepository;
import com.te.bookmydoctor.service.UserService;
import com.te.bookmydoctor.util.ConversionUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LeaveRequestRepository leaveRequestRepository;

	@Override
	public List<DoctorDTO> searchDoctors(String query) {
		return doctorRepository.findByNameContainingOrSpecialistInContaining(query, query).stream()
				.map(doctor -> ConversionUtil.convertToDto(doctor, DoctorDTO.class)).collect(Collectors.toList());
	}

	@Override
	public DoctorDTO getDoctorById(int doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
		return ConversionUtil.convertToDto(doctor, DoctorDTO.class);
	}

	@Override
	public List<AppointmentDTO> getAppointments(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return user.getAppointments().stream()
				.map(appointment -> ConversionUtil.convertToDto(appointment, AppointmentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) {
		try {
			// Convert DTO to entity
			Appointment appointment = ConversionUtil.convertToEntity(appointmentDTO, Appointment.class);

			// Find user and doctor
			User user = userRepository.findById(appointmentDTO.getUser())
					.orElseThrow(() -> new UserNotFoundException("User not found"));
			Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctor())
					.orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

			// Check if doctor is on leave during the requested date
			boolean isOnLeave = leaveRequestRepository
					.existsByDoctorAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStatus(doctor,
							appointment.getDate(), appointment.getDate(), "Approved");

			if (isOnLeave) {
				throw new DoctorOnLeaveException("Doctor is on leave during the requested date");
			}

			// Check if doctor is already booked for the requested time slot
			boolean isAlreadyBooked = appointmentRepository.existsByDoctorAndDateAndTime(doctor, appointment.getDate(),
					appointment.getTime());
			if (isAlreadyBooked) {
				throw new DoctorAlreadyBookedException("Doctor is already booked for the requested time slot");
			}

			// Set user and doctor in appointment
			appointment.setUser(user);
			appointment.setDoctor(doctor);

			// Save appointment
			appointment = appointmentRepository.save(appointment);

			// Convert back to DTO and return
			AppointmentDTO responseDto = ConversionUtil.convertToDto(appointment, AppointmentDTO.class);
			responseDto.setUser(user.getId());
			responseDto.setDoctor(doctor.getId());
			return responseDto;
		} catch (RuntimeException e) {
			// Log the error and rethrow as a runtime exception
			throw new RuntimeException("Failed to book appointment", e);
		}
	}
}
