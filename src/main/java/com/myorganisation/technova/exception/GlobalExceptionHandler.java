package com.myorganisation.technova.exception;

import com.myorganisation.technova.dto.response.GenericResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponseDto> handleRuntimeException(RuntimeException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage("An exception occurred: " + e.getMessage());

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<GenericResponseDto> handleStudentNotFoundException(StudentNotFoundException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage(e.getMessage());

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setSuccess(false);
        genericResponseDto.setMessage(e.getMessage());

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(404));
    }
}
