package com.te.bookmydoctor.dto;

import java.time.LocalDate;

import com.te.bookmydoctor.util.ValidLeaveRequestDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@ValidLeaveRequestDate
@Data
public class LeaveRequestDTO {



    @NotNull(message = "From Date cannot be null")
    @PastOrPresent(message = "From Date must be in the past or present")
    private LocalDate fromDate;

    @NotNull(message = "To Date cannot be null")
    @PastOrPresent(message = "To Date must be in the past or present")
    private LocalDate toDate;

    @NotEmpty(message = "Reason cannot be empty")
    @Size(max = 255, message = "Reason must be less than or equal to 255 characters")
    private String reason;
}
