package com.alexurba.springdatajpapostgresql.exception;

import com.alexurba.springdatajpapostgresql.model.Employee;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class EmployeeExceptionHandler{

    /*
    * We can use the springHandler or our custom handler
    @ExceptionHandler(EmployeeNotFoundException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
    */

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> employeeNotFound(EmployeeNotFoundException ex, WebRequest request){
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
