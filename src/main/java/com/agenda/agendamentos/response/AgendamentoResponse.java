package com.agenda.agendamentos.response;

import com.agenda.agendamentos.entity.Agendamento;

import io.swagger.v3.oas.annotations.media.Schema;

public class AgendamentoResponse {
	
	public AgendamentoResponse(Agendamento agendamentoSaved) {
		
		this.observacao = agendamentoSaved.getObservacao();
		this.dataHora = agendamentoSaved.getDataHora().toString();
	}
	
	@Schema(description = "Observacao")
	private String observacao;
	
	@Schema(description = "Data do agendamento")
	private String dataHora;
}
