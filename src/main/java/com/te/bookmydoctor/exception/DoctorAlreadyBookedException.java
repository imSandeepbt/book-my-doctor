package com.te.bookmydoctor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorAlreadyBookedException extends RuntimeException {
 public String messageString;
}
