package com.agenda.agendamentos.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.agenda.agendamentos.entity.Agendamento;

public interface AgendamentoRepository extends PagingAndSortingRepository<Agendamento, UUID>{

}
