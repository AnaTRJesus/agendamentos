package com.agenda.agendamentos.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.agenda.agendamentos.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, UUID> {

}
