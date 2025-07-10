package com.joaoVictor.ClientManager.services;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoVictor.ClientManager.dto.ClientDTO;
import com.joaoVictor.ClientManager.entities.Client;
import com.joaoVictor.ClientManager.repositories.ClientRepository;
import com.joaoVictor.ClientManager.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


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
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entidade = new Client();
		copy(entidade, dto);
		return new ClientDTO(entidade);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entidade = repository.getReferenceById(id);
			copy(entidade, dto);
			return new ClientDTO(repository.save(entidade));
		}catch (EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}
	
	private void copy(Client entidade, ClientDTO dto) {
		entidade.setName(dto.getName());
		entidade.setCpf(dto.getCpf());
		entidade.setIncome(dto.getIncome());
		entidade.setBirthDate(dto.getBirthDate());
		entidade.setChildren(dto.getChildren());
		entidade = repository.save(entidade);
	}
	
	
}
