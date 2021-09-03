package com.agenda.agendamentos.exception;

import org.springframework.http.HttpStatus;

public enum ErrorException {
	
	SERVICE_NOT_FOUND("Serviço não encontrado", 100);
	
	private final String message;
	private final int code;
	
	ErrorException(String message, int code){
		this.message = message;
		this.code = code;
	}
	public Exceptions toExceptions(HttpStatus httpStatus) {
		return new Exceptions(httpStatus, String.format("%04d",  code), message);
	}
}
