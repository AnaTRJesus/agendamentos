package com.agenda.agendamentos.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ServicoDto {
	
	@Schema(description = "Descricao do servico")
	private String descricao;
	
	@Schema(description = "Preço do serviço")
	private BigDecimal valor;
}
