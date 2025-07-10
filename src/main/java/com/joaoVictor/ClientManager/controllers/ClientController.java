package com.joaoVictor.ClientManager.controllers;


import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id ){
		return ResponseEntity.ok(service.findbyId(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pegeble){
		return ResponseEntity.ok(service.findAll(pegeble));
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto){
		return ResponseEntity.ok(service.update(id, dto));
	}
}
