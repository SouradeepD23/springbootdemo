package com.souradeep.springbootdemo.exception;

import com.souradeep.springbootdemo.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> departmentNotFoundExceptionHandler(DepartmentNotFoundException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
