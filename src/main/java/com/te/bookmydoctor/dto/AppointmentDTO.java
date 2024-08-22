package com.te.bookmydoctor.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.te.bookmydoctor.util.CustomLocalTimeDeserializer;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDTO {

    @NotNull(message = "User ID cannot be null")
    private Integer user;

    @NotNull(message = "Doctor ID cannot be null")
    private Integer doctor;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDate date;

    @NotNull(message = "Time cannot be null")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    private LocalTime time;
}
