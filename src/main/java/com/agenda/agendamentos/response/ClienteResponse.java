package com.agenda.agendamentos.response;

import com.agenda.agendamentos.entity.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteResponse {
	
	public ClienteResponse(Cliente clienteSaved) {
		
		this.cpf = clienteSaved.getCpf();
		this.nome = clienteSaved.getNome();
	}

	@Schema(description = "CPF do cliente")
	private String cpf;
	
	@Schema(description = "Nome do cliente")	
	private String nome;
}
