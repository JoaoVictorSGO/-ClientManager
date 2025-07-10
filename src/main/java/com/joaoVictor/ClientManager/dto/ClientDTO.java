package com.joaoVictor.ClientManager.dto;

import java.time.LocalDate;

import com.joaoVictor.ClientManager.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ClientDTO {
	private Long id;
	@Size(min = 5, max = 50, message = "Nome não pode ter menos de 5 letras e mais de 50")
	@NotBlank(message = "Nome: não pode ser vazio")
	private String name;
	private String cpf;
	@Positive(message = "Renda não pode ser negativa")
	private Double income;
	@PastOrPresent(message = "Data de nascimento: não pode ser data futura")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO() {}
	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
	
}
