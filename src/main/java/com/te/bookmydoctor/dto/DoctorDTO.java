package com.te.bookmydoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Name must have at least 2 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Specialist field cannot be empty")
    private String specialistIn;

    @NotEmpty(message = "Contact number cannot be empty")
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits")
    private String contactNo;

    private double rating;

    @JsonIgnore
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;
}
