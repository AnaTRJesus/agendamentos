package com.agenda.agendamentos.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); 		
    }
    
    @ExceptionHandler(ConstrainException.class)
    public ResponseEntity<Object> handleConstrains(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); 		
    }
}

