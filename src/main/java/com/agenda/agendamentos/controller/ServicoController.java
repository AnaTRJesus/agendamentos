package com.agenda.agendamentos.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agendamentos.dto.ServicoDto;
import com.agenda.agendamentos.entity.Servico;
import com.agenda.agendamentos.exception.ErrorException;
import com.agenda.agendamentos.response.ServicoResponse;
import com.agenda.agendamentos.service.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/servico")
public class ServicoController {
	
	ServicoService service;
	
	public ServicoController(ServicoService service) {
		this.service = service;
	}
	
	
	@Operation(summary= "Criar um novo servico")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ServicoResponse.class))),
				@ApiResponse(
					       responseCode = "422",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
				@ApiResponse(
					       responseCode = "400",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
			})
	@PostMapping
	public ResponseEntity<ServicoResponse> create(@RequestBody ServicoDto servico){
		Servico servicoSaved = service.create(servico);
	    return ResponseEntity.created(URI.create(String.format("/servico/%s", servicoSaved.getCodido().toString()))).body(new ServicoResponse(servicoSaved));
	}

	
	@Operation(summary= "Listar todos os serviços utilizando paginação")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ServicoResponse.class)))})

	@GetMapping
	public Page<Servico> findAllPageable(    
			@RequestParam(
    		value = "status",
    		required = false
    		) String status,
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
	
	@Operation(summary= "Buscar serviço por id")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ServicoResponse.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
				})
	@GetMapping("/{id}")
	public ResponseEntity<ServicoResponse> findById(@PathVariable("id") UUID id){
		Servico servico =  service.findById(id);
		return ResponseEntity.ok(new ServicoResponse(servico));
	}
	
	@Operation(summary= "Excluir serviço por id")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "204",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ServicoResponse.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
				})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@Operation(summary= "Atualizar um servico")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ServicoResponse.class))),
				@ApiResponse(
					       responseCode = "422",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
				@ApiResponse(
					       responseCode = "400",
				           description = "Unsucessful operation",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = ErrorException.class))),

			})	
	@PutMapping
	public ResponseEntity<ServicoResponse> update(@PathVariable("id")UUID id, @RequestBody ServicoDto servico){
		Servico servicoUpdated = service.update(id, servico);
	    return ResponseEntity.created(URI.create(String.format("/servico/%s", servicoUpdated.getCodido().toString()))).body(new ServicoResponse(servicoUpdated));
	}
}
