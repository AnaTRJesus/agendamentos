package com.agenda.agendamentos.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agendamentos.dto.ClienteDto;
import com.agenda.agendamentos.entity.Cliente;
import com.agenda.agendamentos.exception.ConstrainException;
import com.agenda.agendamentos.exception.NotFoundException;
import com.agenda.agendamentos.repository.ClienteRepository;

@Service
@Transactional

public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
    @Transactional(readOnly = true)
	public Page<Cliente> findAllPageable(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
    @Transactional(readOnly = true)
	public Iterable<Cliente> findAll(){
		return clienteRepository.findAll();
	}
    
    @Transactional(readOnly = true)
    public Cliente findById(UUID id) {
    	return clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("O cliente não foi encontrado"));
		
    }
	
	public Cliente create(ClienteDto entity) {
		
		if(entity.getCpf() == null || entity.getCpf().equals("")) throw new ConstrainException("O campo CPF deve estar preenchido");
		
		if(entity.getNome() == null || entity.getNome().equals("")) throw new ConstrainException("O campo Nome deve estra preenchido");

		Cliente cliente = Cliente.builder().cpf(entity.getCpf()).nome(entity.getNome()).build();
		return clienteRepository.save(cliente);
	}
	
	public void delete(UUID id) {
	
		Cliente Cliente = clienteRepository.findById(id)
		.orElseThrow(() -> new NotFoundException("O cliente não foi encontrado"));
		
		clienteRepository.delete(Cliente);
	}
	
	public Cliente update(UUID id, ClienteDto entity) {
		
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("O cliente não foi encontrado"));
		
		cliente.setCpf(Objects.requireNonNullElse(entity.getCpf(), cliente.getCpf()));
		cliente.setNome(Objects.requireNonNullElse(entity.getNome(), cliente.getNome()));
		
		clienteRepository.save(cliente);
		
		return cliente;
	}
}
