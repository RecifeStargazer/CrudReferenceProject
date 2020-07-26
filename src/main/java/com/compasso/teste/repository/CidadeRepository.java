package com.compasso.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.teste.domain.Cidade;
import com.compasso.teste.domain.CidadeId;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, CidadeId>{
	
	@Query("SELECT cid from Cidade cid WHERE cid.id.cidade = :nomeCidade")
	public List<Cidade> buscarPeloNome(String nomeCidade);
	
	@Query("SELECT cid from Cidade cid WHERE cid.id.estado = :nomeEstado")
	public List<Cidade> buscarPorEstado(String nomeEstado);

}
