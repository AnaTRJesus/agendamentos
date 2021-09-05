package com.agenda.agendamentos.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agendamentos.dto.ServicoDto;
import com.agenda.agendamentos.entity.Servico;
import com.agenda.agendamentos.exception.NotFoundException;
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
				.orElseThrow(() -> new NotFoundException());
		
    }
	
	public Servico create(ServicoDto entity) {
		
		Servico servico = Servico.builder().descricao(entity.getDescricao()).valor(entity.getValor()).build();
		return servicoRepository.save(servico);
	}
	
	public void delete(UUID id) {
	
		Servico servico = servicoRepository.findById(id)
		.orElseThrow(() -> new NotFoundException());
		
		servicoRepository.delete(servico);
	}
	
	public Servico update(UUID id, ServicoDto entity) {
		
		Servico servico = servicoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
		
		servico.setDescricao(Objects.requireNonNullElse(entity.getDescricao(), servico.getDescricao()));
		if(entity.getValor() != null) servico.setValor(entity.getValor());
		
		servicoRepository.save(servico);
		
		return servico;
	}
}
