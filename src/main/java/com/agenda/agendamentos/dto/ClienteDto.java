package com.agenda.agendamentos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClienteDto {
	
	@Schema(description = "Nome do cliente", required = true)
	private String nome;
	
	@Schema(description = "CPF do cliente ", required = true)
	private String cpf;
}
