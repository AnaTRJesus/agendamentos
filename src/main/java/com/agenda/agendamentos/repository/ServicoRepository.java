package com.agenda.agendamentos.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.agenda.agendamentos.entity.Servico;

@Repository
public interface ServicoRepository extends PagingAndSortingRepository<Servico, UUID> {

}
