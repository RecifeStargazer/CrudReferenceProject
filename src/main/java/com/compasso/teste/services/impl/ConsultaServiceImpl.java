package com.compasso.teste.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;
import com.compasso.teste.domain.Cidade;
import com.compasso.teste.domain.Cliente;
import com.compasso.teste.repository.CidadeRepository;
import com.compasso.teste.repository.ClienteRepository;
import com.compasso.teste.services.ConsultaService;

import javassist.NotFoundException;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	private static final String FORMATO_DATA = "dd/MM/yyyy";

	@Autowired
	ModelMapper mapper;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public List<CidadeDTO> getListaCidadesPorNome(String nomeCidade) {
		List<CidadeDTO> retorno = null;
		List<Cidade> listaCidadeDomain = cidadeRepository.buscarPeloNome(nomeCidade);
		retorno = mapper.map(listaCidadeDomain, new TypeToken<List<CidadeDTO>>() {
		}.getType());
		return retorno;
	}

	@Override
	public ClienteDTO getClientePorId(Long idCliente) throws NotFoundException {
		ClienteDTO retorno=null;
		Cliente clienteDomain;
		try {
			clienteDomain= clienteRepository.findById(idCliente).get();
		}catch(NoSuchElementException nsee) {
			throw new NotFoundException("Cliente com id"+idCliente+"Não Existe");
		}
		retorno = mapper.map(clienteDomain, ClienteDTO.class);
		retorno.setDataDeAniversario(dataAniversarioFromDomain(clienteDomain.getDataDeNascimento()));
		return retorno;
	}

	@Override
	public List<CidadeDTO> getListaCidadesPorEstado(String nomeEstado) {
		List<CidadeDTO> retorno = null;
		List<Cidade> listaCidadeDomain = cidadeRepository.buscarPorEstado(nomeEstado);
		retorno = mapper.map(listaCidadeDomain, new TypeToken<List<CidadeDTO>>() {
		}.getType());
		return retorno;
	}

	@Override
	public List<ClienteDTO> getListaClientePorNome(String nomeCliente) {
		List<ClienteDTO> retorno = null;
		List<Cliente> listaClienteDomain = clienteRepository.findAllByNome(nomeCliente);
		retorno = mapper.map(listaClienteDomain, new TypeToken<List<ClienteDTO>>() {
		}.getType());
		retorno.stream().forEach(clienteDto ->{
			listaClienteDomain.stream().forEach(clienteDomain ->{
				if(clienteDto.getId()==clienteDomain.getId()) {
					clienteDto.setDataDeAniversario(dataAniversarioFromDomain(clienteDomain.getDataDeNascimento()));;
				}
			});
		});
		return retorno;
	}

	private String dataAniversarioFromDomain(Calendar calendar) {
		DateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
		String retorno = dateFormat.format(calendar.getTime());
		return retorno;
	}

}
