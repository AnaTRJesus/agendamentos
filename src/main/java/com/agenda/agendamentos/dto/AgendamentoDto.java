package com.agenda.agendamentos.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AgendamentoDto {
	
	@Schema(description = "Id do cliente", required = true)
	private UUID clienteID;
	
	@Schema(description = "Id do serviço", required = true)
	private UUID servicoID;
	
	@Schema(description = "Data e hora do agendamento", required = true)
	private String dataHora;
	
	@Schema(description = "Observação do agendamento", required = true)
	private String observacao;

}
