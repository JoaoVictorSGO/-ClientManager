package com.joaoVictor.ClientManager.services;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joaoVictor.ClientManager.dto.ClientDTO;
import com.joaoVictor.ClientManager.entities.Client;
import com.joaoVictor.ClientManager.repositories.ClientRepository;
import com.joaoVictor.ClientManager.services.exceptions.DatabaseException;
import com.joaoVictor.ClientManager.services.exceptions.ResourceNotFoundException;


import jakarta.persistence.EntityNotFoundException;


@Service
public class ClientService {
	 
	private ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO buscarPorId(Long id){
		return new ClientDTO(repository
				.findById(id)
				.orElseThrow(() ->  new ResourceNotFoundException("cliente inexistente")));
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> buscarTodos(Pageable pegeable){
		Page<Client> result = repository.findAll(pegeable);
		return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO inserir(ClientDTO dto) {
		Client entidade = new Client();
		copiar(entidade, dto);
		return new ClientDTO(entidade);
	}
	
	@Transactional
	public ClientDTO atualizar(Long id, ClientDTO dto) {
		try {
			Client entidade = repository.getReferenceById(id);
			copiar(entidade, dto);
			return new ClientDTO(repository.save(entidade));
		}catch (EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}
	
	@Transactional(propagation  = Propagation.SUPPORTS)
	public void deletar(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DatabaseException e) {
			throw new DatabaseException("Operação inválida: esse dado está relacionado com outro.");
		}
		repository.deleteById(id);
	}
	
	private void copiar(Client entidade, ClientDTO dto) {
		entidade.setName(dto.getName());
		entidade.setCpf(dto.getCpf());
		entidade.setIncome(dto.getIncome());
		entidade.setBirthDate(dto.getBirthDate());
		entidade.setChildren(dto.getChildren());
		entidade = repository.save(entidade);
	}
	
	
	
}
