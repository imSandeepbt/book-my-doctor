package com.te.bookmydoctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseStructureDto {
private boolean error;
private String message;
private Object data;
}
