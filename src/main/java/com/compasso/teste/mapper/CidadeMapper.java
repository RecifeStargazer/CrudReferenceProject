package com.compasso.teste.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.compasso.teste.app.api.model.CidadeDTO;
import com.compasso.teste.domain.Cidade;

//@Configuration
public class CidadeMapper {

//	@Bean
//	public ModelMapper cidade() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		PropertyMap<CidadeDTO, Cidade> cidadeDtoToDomain = new PropertyMap<CidadeDTO, Cidade>() {
//
//			@Override
//			protected void configure() {
//				when(Conditions.isNotNull()).map().getId().setCidade(source.getCidade());
//				when(Conditions.isNotNull()).map().getId().setEstado(source.getEstado());
//			}
//
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