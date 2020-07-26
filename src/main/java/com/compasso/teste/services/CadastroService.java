package com.compasso.teste.services;

import java.text.ParseException;

import javax.validation.Valid;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;

import javassist.NotFoundException;

public interface CadastroService {

	CidadeDTO cadastrarCidade(@Valid CidadeDTO cidade);

	ClienteDTO cadastrarCliente(@Valid ClienteDTO cliente) throws ParseException, NotFoundException;

	ClienteDTO editarCliente(Long id, @Valid ClienteDTO cliente) throws NotFoundException, ParseException;
	
	void deleteCliente(Long id) throws NotFoundException;

	ClienteDTO editarNomeCliente(Long id, String nomeCliente) throws NotFoundException;
}
