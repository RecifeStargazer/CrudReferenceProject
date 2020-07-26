package com.compasso.teste.services;

import java.util.List;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;

import javassist.NotFoundException;

public interface ConsultaService {

	List<CidadeDTO> getListaCidadesPorNome(String nomeCidade);

	ClienteDTO getClientePorId(Long idCliente) throws NotFoundException;

	List<CidadeDTO> getListaCidadesPorEstado(String nomeEstado);

	List<ClienteDTO> getListaClientePorNome(String nomeCliente);


}
