package com.agenda.agendamentos.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class Exceptions extends RuntimeException{ 
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private String code;
	
	public Exceptions( HttpStatus httpStatus, String code, String message) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}
}
