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

import com.agenda.agendamentos.dto.ClienteDto;
import com.agenda.agendamentos.entity.Cliente;
import com.agenda.agendamentos.exception.NotFoundException;
import com.agenda.agendamentos.response.ClienteResponse;
import com.agenda.agendamentos.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	
	@Operation(summary= "Criar um novo cliente")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ClienteResponse.class))),
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
	@PostMapping
	public ResponseEntity<ClienteResponse> create(@RequestBody ClienteDto cliente){
		Cliente clienteSaved = service.create(cliente);
	    return ResponseEntity.created(URI.create(String.format("/servico/%s", clienteSaved.getId().toString()))).body(new ClienteResponse(clienteSaved));
	}

	
	@Operation(summary= "Listar todos os clientes utilizando paginação")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ClienteResponse.class)))})

	@GetMapping
	public Page<Cliente> findAllPageable(    
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
	
	@Operation(summary= "Buscar cliente por id")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "200",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ClienteResponse.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = Exception.class))),
				})
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> findById(@PathVariable("id") UUID id){
		Cliente cliente =  service.findById(id);
		return ResponseEntity.ok(new ClienteResponse(cliente));
	}
	
	@Operation(summary= "Excluir cliente por id")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "204",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ClienteResponse.class))),
				@ApiResponse(
					       responseCode = "404",
				           description = "Not found",
				           content =
				       		   @Content(
				       				mediaType = "application/json",
				       				schema = @Schema(implementation = NotFoundException.class))),
				})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@Operation(summary= "Atualizar um cliente")
	@ApiResponses(
			value = {
				@ApiResponse(
			       responseCode = "201",
		           description = "Sucessful operation",
		           content =
		       		   @Content(
		       				mediaType = "application/json",
		       				schema = @Schema(implementation = ClienteResponse.class))),
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
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> update(@PathVariable("id")UUID id, @RequestBody ClienteDto servico){
		Cliente clienteUpdated = service.update(id, servico);
	    return ResponseEntity.created(URI.create(String.format("/servico/%s", clienteUpdated.getId()))).body(new ClienteResponse(clienteUpdated));
	}
}
