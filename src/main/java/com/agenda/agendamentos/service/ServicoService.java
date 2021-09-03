package com.agenda.agendamentos.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agendamentos.dto.ServicoDto;
import com.agenda.agendamentos.entity.Servico;
import com.agenda.agendamentos.exception.ErrorException;
import com.agenda.agendamentos.repository.ServicoRepository;

@Service
@Transactional

public class ServicoService {
	
	private final ServicoRepository servicoRepository;
	
	public ServicoService(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}
	
    @Transactional(readOnly = true)
	public Page<Servico> findAllPageable(Pageable pageable) {
		return servicoRepository.findAll(pageable);
	}
	
    @Transactional(readOnly = true)
	public Iterable<Servico> findAll(){
		return servicoRepository.findAll();
	}
    
    @Transactional(readOnly = true)
    public Servico findById(UUID id) {
    	return servicoRepository.findById(id)
				.orElseThrow(() -> ErrorException.SERVICE_NOT_FOUND.toExceptions(HttpStatus.NOT_FOUND));
		
    }
	
	public Servico create(ServicoDto entity) {
		
		Servico servico = Servico.builder().descricao(entity.getDescricao()).valor(entity.getValor()).build();
		return servicoRepository.save(servico);
	}
	
	public void delete(UUID id) {
	
		Servico servico = servicoRepository.findById(id)
		.orElseThrow(() -> ErrorException.SERVICE_NOT_FOUND.toExceptions(HttpStatus.NOT_FOUND));
		
		servicoRepository.delete(servico);
	}
	
	public Servico update(UUID id, ServicoDto entity) {
		
		Servico servico = servicoRepository.findById(id)
				.orElseThrow(() -> ErrorException.SERVICE_NOT_FOUND.toExceptions(HttpStatus.NOT_FOUND));
		
		servico.setDescricao(Objects.requireNonNullElse(entity.getDescricao(), servico.getDescricao()));
		servico.setValor((BigDecimal) Objects.requireNonNullElse(entity.getDescricao(), servico.getValor()));
		servicoRepository.save(servico);
		
		return servico;
	}
}
