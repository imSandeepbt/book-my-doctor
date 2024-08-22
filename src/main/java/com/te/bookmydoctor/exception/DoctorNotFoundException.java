package com.te.bookmydoctor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DoctorNotFoundException extends RuntimeException {
public String message;
}
