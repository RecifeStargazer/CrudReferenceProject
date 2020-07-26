package com.compasso.teste.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compasso.teste.app.api.ConsultaApi;
import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;
import com.compasso.teste.services.ConsultaService;

import javassist.NotFoundException;

@Controller
public class ConsultaApiController implements ConsultaApi{

	@Autowired
	ConsultaService consultaService;
	
	@Override
	 @RequestMapping(value = "/consulta/{nome_cidade}/buscarListaCidadesPorNome",
     produces = { "application/json" }, 
     consumes = { "application/json" },
     method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> getListaCidadesPorNome(String nomeCidade) {
		List<CidadeDTO> retorno = consultaService.getListaCidadesPorNome(nomeCidade);
		return new ResponseEntity<List<CidadeDTO>>(retorno, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/consulta/{id_cliente}/buscarClientePorId",
    produces = { "application/json" }, 
    consumes = { "application/json" },
    method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> getClientePorId(Long idCliente) {
		ClienteDTO retorno;
		try {
			retorno = consultaService.getClientePorId(idCliente);
		} catch (NotFoundException e) {
			return new  ResponseEntity("Cliente com id="+idCliente+"não existe na base de dados",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ClienteDTO>(retorno, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/consulta/{nome_estado}/buscarListaCidadesPorEstado",
    produces = { "application/json" }, 
    consumes = { "application/json" },
    method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> getListaCidadesPorEstado(String nomeEstado) {
		List<CidadeDTO> retorno = consultaService.getListaCidadesPorEstado(nomeEstado);
		return new ResponseEntity<List<CidadeDTO>>(retorno, HttpStatus.OK);
	}

	@Override
	 @RequestMapping(value = "/consulta/{nome_cliente}/buscarListaClientePorNome",
     produces = { "application/json" }, 
     consumes = { "application/json" },
     method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> getListaClientePorNome(String nomeCliente) {
		List<ClienteDTO> retorno = consultaService.getListaClientePorNome(nomeCliente);
		return new ResponseEntity<List<ClienteDTO>>(retorno, HttpStatus.OK);
	}

}
