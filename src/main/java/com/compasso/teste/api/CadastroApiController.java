package com.compasso.teste.api;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compasso.teste.app.api.CadastroApi;
import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;
import com.compasso.teste.services.CadastroService;

import javassist.NotFoundException;

@Controller
public class CadastroApiController implements CadastroApi {

	private static final String MSG_BAD_REQUEST = "Verifique as informações enviadas";
	private static final String MSG_NOT_FOUND = "Registro não encontrado";
	private static final String MSG_CLIENTE_REMOVIDO = "Cliente Removido da base de dados";

	@Autowired
	CadastroService cadastroService;

	@Override
	@RequestMapping(value = "/cadastro/cadastrarCidade", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<CidadeDTO> addCidade(@Valid CidadeDTO cidade) {
		try {
			CidadeDTO retorno = cadastroService.cadastrarCidade(cidade);
			return new ResponseEntity<CidadeDTO>(retorno, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/cadastro/cadastrarCliente", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	@Override
	public ResponseEntity<ClienteDTO> addCliente(@Valid ClienteDTO cliente) {
		try {
			ClienteDTO retorno = cadastroService.cadastrarCliente(cliente);
			return new ResponseEntity<ClienteDTO>(retorno, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		} catch (ParseException e) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@RequestMapping(value = "/cadastro/{id}/editarCliente", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ClienteDTO> editCliente(Long id, @Valid ClienteDTO cliente) {
		try {
			ClienteDTO retorno = cadastroService.editarCliente(id, cliente);
			return new ResponseEntity<ClienteDTO>(retorno, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity(MSG_NOT_FOUND, HttpStatus.NOT_FOUND);
		} catch (ParseException e) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@RequestMapping(value = "/cadastro/{id}/deletarCliente", produces = {
			"application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCliente(Long id) {
		try {
			cadastroService.deleteCliente(id);
			return new ResponseEntity(MSG_CLIENTE_REMOVIDO, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity(MSG_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@RequestMapping(value = "/cadastro/{id}/{nomeCliente}/editarNomeCliente", produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ClienteDTO> editNomeCliente(Long id, String nomeCliente) {
		try {
			ClienteDTO retorno = cadastroService.editarNomeCliente(id, nomeCliente);
			return new ResponseEntity<ClienteDTO>(retorno, HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity(MSG_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException nfe) {
			return new ResponseEntity(MSG_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}
}
