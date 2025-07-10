package com.joaoVictor.ClientManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoVictor.ClientManager.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
