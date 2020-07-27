package com.compasso.teste.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;
import com.compasso.teste.domain.Cidade;
import com.compasso.teste.domain.Cliente;
import com.compasso.teste.enums.SexoEnum;
import com.compasso.teste.repository.CidadeRepository;
import com.compasso.teste.repository.ClienteRepository;
import com.compasso.teste.services.CadastroService;

import javassist.NotFoundException;

@Service
public class CadastroServiceImpl implements CadastroService {

	private static final String FORMATO_DATA = "dd/MM/yyyy";
	
	private static final String MSG_NOT_FOUND = "Registro não encontrado";
	
	private static final String MSG_INVALID_ARGUMENT = "Parâmetro invalido";
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Override
	public CidadeDTO cadastrarCidade(@Valid CidadeDTO cidade) {
		CidadeDTO retorno = null;
		if(validarCidade(cidade)) {
			Cidade cidadeDomain = mapper.map(cidade, Cidade.class);
			Cidade cidadeSalva = cidadeRepository.save(cidadeDomain);
			retorno = mapper.map(cidadeSalva, CidadeDTO.class);		
		}
		return retorno;		
	}

	@Override
	public ClienteDTO cadastrarCliente(@Valid ClienteDTO cliente) throws ParseException, NotFoundException {
		ClienteDTO retorno = null;
		if(validarCliente(cliente) && cidadeExiste(cliente.getCidade())) {
			Cliente clienteDomain = mapper.map(cliente, Cliente.class);
			clienteDomain.setSexo(clienteDomain.getSexo().toUpperCase());
			clienteDomain.setDataDeNascimento(dataAniversarioFromDTO(cliente.getDataDeAniversario()));
			Cliente clienteSalvo = clienteRepository.save(clienteDomain);
			retorno = mapper.map(clienteSalvo, ClienteDTO.class);
			retorno.setDataDeAniversario(dataAniversarioFromDomain(clienteSalvo.getDataDeNascimento()));
		}
		return retorno;
	}

	@Override
	public ClienteDTO editarCliente(Long id, @Valid ClienteDTO cliente) throws NotFoundException, ParseException {
		ClienteDTO retorno = null;
		if(validarCliente(cliente) && cidadeExiste(cliente.getCidade())) {
			Cliente clienteDomain;
			try {
				clienteDomain = clienteRepository.findById(id).get();
			}catch(Exception e) {
				throw new NotFoundException(MSG_NOT_FOUND);
			}
			clienteDomain = mapper.map(cliente, Cliente.class);
			clienteDomain.setDataDeNascimento(dataAniversarioFromDTO(cliente.getDataDeAniversario()));
			clienteDomain.setSexo(clienteDomain.getSexo().toUpperCase());
			Cliente clienteSalvo = clienteRepository.save(clienteDomain);
			retorno = mapper.map(clienteSalvo, ClienteDTO.class);
			retorno.setDataDeAniversario(dataAniversarioFromDomain(clienteSalvo.getDataDeNascimento()));
		}
		return retorno;		
	}
	
	@Override
	public ClienteDTO editarNomeCliente(Long id, String nomeCliente) throws NotFoundException {
		ClienteDTO retorno = null;
		if(validarNome(nomeCliente)) {
			Cliente clienteDomain;
			try {
				clienteDomain = clienteRepository.findById(id).get();
			}catch(Exception e) {
				throw new NotFoundException(nomeCliente);
			}
			clienteDomain.setNome(nomeCliente);
			Cliente clienteSalvo = clienteRepository.save(clienteDomain);
			retorno = mapper.map(clienteSalvo, ClienteDTO.class);
			retorno.setDataDeAniversario(dataAniversarioFromDomain(clienteSalvo.getDataDeNascimento()));
		}
		return retorno;		
	}

	@Override
	public void deleteCliente(Long id) throws NotFoundException {
		try {
			clienteRepository.deleteById(id);
		}catch(EmptyResultDataAccessException erdae) {
			throw new NotFoundException(MSG_NOT_FOUND);
		}
		
	}
	
	private Calendar dataAniversarioFromDTO(String dataAniversario) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
		Calendar retorno = dateFormat.getCalendar();
		Date data = dateFormat.parse(dataAniversario);
		retorno.setTime(data);
		return retorno;
	}
	
	private String dataAniversarioFromDomain(Calendar calendar) {
		DateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
		String retorno = dateFormat.format(calendar.getTime());
		return retorno;
	}
	
	private boolean validarIdade(ClienteDTO cliente) {
		if(cliente.getIdade()!=null && cliente.getIdade()>0 && cliente.getIdade()<140) {
			return true;
		}
		return false;
	}
	private boolean validarCidade(CidadeDTO cidade) {
		if(cidade.getEstado()!=null&&cidade.getCidade()!=null) {
			return true;
		}
		return false;
	}
	
	private boolean validarCidade(ClienteDTO cliente) {
		if(cliente.getCidade()!=null) {
			if(cliente.getCidade().getEstado()!=null&&cliente.getCidade().getCidade()!=null) {
				return true;
			}
		}	
		return false;
	}
	
	private boolean cidadeExiste(CidadeDTO cidade) throws NotFoundException {
		Cidade cidadeDomain = mapper.map(cidade, Cidade.class);
		boolean retorno = cidadeRepository.existsById(cidadeDomain.getId());
		if(!retorno) {
			throw new NotFoundException(MSG_NOT_FOUND);
		}
		return cidadeRepository.existsById(cidadeDomain.getId());
	}
	
	private boolean validarCliente(ClienteDTO cliente) {
		if(validarNome(cliente)
				&&validarSexo(cliente)
				&&validarAniversario(cliente)
				&&validarCidade(cliente)
				&&validarIdade(cliente)) {
			return true;
		}
		return false;
	}
	
	private boolean validarNome(ClienteDTO cliente) {
		if(cliente.getNome()!=null) {
			if(!cliente.getNome().chars().anyMatch(Character::isDigit)) {
				return true;
			};
		}
		throw new IllegalArgumentException(MSG_INVALID_ARGUMENT);
		
	}
	
	private boolean validarNome(String nome) {
			if(!nome.chars().anyMatch(Character::isDigit)) {
				return true;
			};
		throw new IllegalArgumentException(MSG_INVALID_ARGUMENT);
		
	}
	
	private boolean validarSexo(ClienteDTO cliente) {
		if (cliente.getSexo()!=null) {
			String sexoMaiusculo = cliente.getSexo().toUpperCase();
			if (SexoEnum.MASCULINO.getDescricao().equals(sexoMaiusculo)
					||SexoEnum.FEMININO.getDescricao().equals(sexoMaiusculo)){
				return true;
			}
		}
		return false;
	}
	
	private boolean validarAniversario(ClienteDTO cliente) {
		DateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
		dateFormat.setLenient(false);
		try {
			Date date = dateFormat.parse(cliente.getDataDeAniversario());
			return true;
		}catch(ParseException e){
			throw new IllegalArgumentException(MSG_INVALID_ARGUMENT);
		}catch(NullPointerException npe) {
			throw new IllegalArgumentException(MSG_INVALID_ARGUMENT);
		}
	}
}
