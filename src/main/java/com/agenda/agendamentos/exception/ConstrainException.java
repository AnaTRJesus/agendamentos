package com.agenda.agendamentos.exception;

public class ConstrainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstrainException(String detail) {
		super(detail);
	}
}
