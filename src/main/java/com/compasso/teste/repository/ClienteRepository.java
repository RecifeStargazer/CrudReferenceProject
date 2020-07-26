package com.compasso.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.teste.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	 List<Cliente> findAllByNome(String nome);

}
