package com.compasso.teste.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.app.api.model.ClienteDTO;
import com.compasso.teste.domain.Cidade;
import com.compasso.teste.domain.Cliente;

@Configuration
public class ClienteMapper {
//	@Bean
//	public ModelMapper cliente() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		PropertyMap<ClienteDTO, Cliente> clienteDtoToDomain = new PropertyMap<ClienteDTO, Cliente>() {
//
//			@Override
//			protected void configure() {
//				when(Conditions.isNotNull());	
//			}
//		};
//		PropertyMap<Cidade, CidadeDTO> cidadeDomainToDto = new PropertyMap<Cidade, CidadeDTO>() {
//
//			@Override
//			protected void configure() {
//				when(Conditions.isNotNull()).map().setCidade(source.getId().getCidade());
//				when(Conditions.isNotNull()).map().setEstado(source.getId().getEstado());
//			}
//
//		};
//		modelMapper.addMappings(cidadeDtoToDomain);
//		modelMapper.addMappings(cidadeDomainToDto);
//		return modelMapper;
//
//	}
}
