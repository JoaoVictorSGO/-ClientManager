package com.joaoVictor.ClientManager.services;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoVictor.ClientManager.dto.ClientDTO;
import com.joaoVictor.ClientManager.entities.Client;
import com.joaoVictor.ClientManager.repositories.ClientRepository;
import com.joaoVictor.ClientManager.services.exceptions.ResourceNotFoundException;


@Service
public class ClientService {
	 
	private ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findbyId(Long id){
		return new ClientDTO(repository
				.findById(id)
				.orElseThrow(() ->  new ResourceNotFoundException("cliente inexistente")));
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pegeable){
		Page<Client> result = repository.findAll(pegeable);
		return result.map(x -> new ClientDTO(x));
	}
}
