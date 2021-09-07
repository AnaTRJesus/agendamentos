package com.agenda.agendamentos.service;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agendamentos.dto.AgendamentoDto;
import com.agenda.agendamentos.entity.Agendamento;
import com.agenda.agendamentos.exception.ConstrainException;
import com.agenda.agendamentos.exception.NotFoundException;
import com.agenda.agendamentos.repository.AgendamentoRepository;

@Service
@Transactional
public class AgendamentoService {
	
	private final AgendamentoRepository agendamentoRepository;
	private final ClienteService clienteService;
	private final ServicoService servicoService;
	
	public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteService clienteService, ServicoService servicoService) {
		this.agendamentoRepository = agendamentoRepository;
		this.clienteService = clienteService;
		this.servicoService = servicoService;
	}
	
    @Transactional(readOnly = true)
	public Page<Agendamento> findAllPageable(Pageable pageable) {
		return agendamentoRepository.findAll(pageable);
	}
    
    public Agendamento create(AgendamentoDto agendamentoDto) {
        
    	    	
		if(agendamentoDto.getClienteID() == null || agendamentoDto.getClienteID().equals("")) throw new ConstrainException("O Id do cliente deve ser informado");
		if(agendamentoDto.getServicoID() == null || agendamentoDto.getServicoID().equals("")) throw new ConstrainException("O Id do serviço deve ser informado");
		if(agendamentoDto.getObservacao() == null || agendamentoDto.getObservacao().equals("")) throw new ConstrainException("A observação deve ser informada");
		if(agendamentoDto.getDataHora()== null || agendamentoDto.getDataHora().equals("")) throw new ConstrainException("A Data-Hora deve ser informada");

		
        Timestamp timestamp = Timestamp.valueOf(agendamentoDto.getDataHora());
		new Agendamento();
		Agendamento agendamento = Agendamento.builder().cliente(clienteService.findById
				(agendamentoDto.getClienteID())).servico(servicoService.findById(agendamentoDto.getServicoID()))
				.observacao(agendamentoDto.getObservacao()).dataHora(timestamp).build();
		
		Agendamento agendamentoSaved = agendamentoRepository.save(agendamento);

		return agendamentoSaved;
    }
    
    public Agendamento remarcarAgendamento(String dataHora, UUID id) {
    	
		if(dataHora== null || dataHora.equals("")) throw new ConstrainException("A Data-Hora deve ser informada");
		
		Agendamento agendamento = findById(id);
        Timestamp timestamp = Timestamp.valueOf(dataHora);

		agendamento.setDataHora(timestamp);
		
		return agendamentoRepository.save(agendamento);
    }
    
    public Agendamento findById(UUID id) {
    	return agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("O agendamento não foi encontrado"));
    }
    
    @Transactional(readOnly = true)
	public Page<Agendamento> findAllPageableAndSort(int page, int size) {
		return agendamentoRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "dataHora").and(Sort.by("servico.valor"))));
	}
}
