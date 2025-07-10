package com.joaoVictor.ClientManager.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoVictor.ClientManager.dto.ClientDTO;
import com.joaoVictor.ClientManager.services.ClientService;

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
}
