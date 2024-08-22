package com.te.bookmydoctor.util;

import com.te.bookmydoctor.dto.LeaveRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class LeaveRequestDateValidator implements ConstraintValidator<ValidLeaveRequestDate, LeaveRequestDTO> {
    @Override
    public boolean isValid(LeaveRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getFromDate() != null && dto.getToDate() != null) {
            return !dto.getToDate().isBefore(dto.getFromDate());
        }
        return true;
    }
}
