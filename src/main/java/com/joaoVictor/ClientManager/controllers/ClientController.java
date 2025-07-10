package com.joaoVictor.ClientManager.controllers;


import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaoVictor.ClientManager.dto.ClientDTO;
import com.joaoVictor.ClientManager.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
	
	private ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> buscarPorId(@PathVariable Long id ){
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> buscarTodos(Pageable pegeble){
		return ResponseEntity.ok(service.buscarTodos(pegeble));
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> inserir(@Valid @RequestBody ClientDTO dto) {
		dto = service.inserir(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<ClientDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClientDTO dto){
		return ResponseEntity.ok(service.atualizar(id, dto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
