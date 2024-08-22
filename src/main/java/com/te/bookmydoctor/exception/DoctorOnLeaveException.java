package com.te.bookmydoctor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorOnLeaveException extends RuntimeException {
public String message;
}
