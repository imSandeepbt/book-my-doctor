package com.te.bookmydoctor.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.bookmydoctor.dto.ResponseStructureDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ResponseStructureDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        List<String> errors = ex.getBindingResult()
	                                .getAllErrors()
	                                .stream()
	                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                                .collect(Collectors.toList());
	        ResponseStructureDto response = new ResponseStructureDto(true, "Validation failed", errors);
	        return ResponseEntity.badRequest().body(response);}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseStructureDto> handleRuntimeExceptions(RuntimeException ex) {
        ResponseStructureDto response = new ResponseStructureDto(true, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ResponseStructureDto> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        ResponseStructureDto response = new ResponseStructureDto(true, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
