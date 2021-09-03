package com.agenda.agendamentos.response;

import java.math.BigDecimal;

import com.agenda.agendamentos.entity.Servico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ServicoResponse {
	
	public ServicoResponse(Servico servicoSaved) {
		
		this.descricao = servicoSaved.getDescricao();
		this.valor = servicoSaved.getValor();
	}

	@Schema(description = "Descrição do serviço")
	private String descricao;
	
	@Schema(description = "Preço do serviço")	
	private BigDecimal valor;
}
