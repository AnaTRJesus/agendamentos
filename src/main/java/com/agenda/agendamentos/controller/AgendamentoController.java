package com.agenda.agendamentos.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agendamentos.dto.AgendamentoDto;
import com.agenda.agendamentos.entity.Agendamento;
import com.agenda.agendamentos.exception.NotFoundException;
import com.agenda.agendamentos.response.AgendamentoResponse;
import com.agenda.agendamentos.service.AgendamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	AgendamentoService service;
	
	public AgendamentoController(AgendamentoService service) {
		this.service = service;
	}

	
	@Operation(summary= "Criar um novo agendamento")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation"),
				
				@ApiResponse(
					       responseCode = "422",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = Exception.class))),
				@ApiResponse(
					       responseCode = "400",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = Exception.class))),
			})
	@PostMapping()
	public ResponseEntity<AgendamentoResponse> create(@RequestBody AgendamentoDto agendamento){
		Agendamento agendamentoSaved = service.create(agendamento);
	    return ResponseEntity.created(URI.create(String.format("/agendamento/%s", agendamentoSaved.getId().toString()))).build();
	}

	
	@Operation(summary= "Listar todos os agendamentos utilizando paginação")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = AgendamentoResponse.class)))})

	@GetMapping
	public Page<Agendamento> findAllPageable(    
    @RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
    @RequestParam(
            value = "size",
            required = false,
            defaultValue = "10") int size){
		
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "id");
		return service.findAllPageable(pageRequest);
	}
	

	@Operation(summary= "Atualizar horário do agendamento")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = AgendamentoResponse.class))),
				@ApiResponse(
					       responseCode = "422",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = Exception.class))),
				@ApiResponse(
					       responseCode = "400",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = Exception.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = NotFoundException.class))),

			})	

	@PutMapping("remarcar/{id}")
	public ResponseEntity<AgendamentoResponse> update(@PathVariable("id")UUID id, @RequestBody String dataHora){
		Agendamento agendamentoUpdated = service.remarcarAgendamento(dataHora, id);
	    return ResponseEntity.created(URI.create(String.format("/agendamento/%s", agendamentoUpdated.getId()))).body(new AgendamentoResponse(agendamentoUpdated));
	}
	
	
	@Operation(summary= "Listar todos os agendamentos utilizando paginação e ordenação por data hora e valor")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = AgendamentoResponse.class)))})

	@GetMapping("sorted")
	public Page<Agendamento> findAllPageableSorted(    
    @RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
    @RequestParam(
            value = "size",
            required = false,
            defaultValue = "10") int size){
		
		return service.findAllPageableAndSort(page,size);
	}
}
