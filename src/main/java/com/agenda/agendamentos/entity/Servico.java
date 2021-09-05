package com.agenda.agendamentos.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private UUID id;
	
	@Column
	private String descricao;
	
	@Column
	private BigDecimal valor;
}
