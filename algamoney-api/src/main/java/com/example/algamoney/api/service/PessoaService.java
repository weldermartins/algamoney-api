package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;	
	

	public Pessoa buscaPeloCodigoPessoa(Long codigo) {
			Pessoa pessoaSalva = pessoaRepository.findById(codigo)
		                .orElseThrow(() -> new EmptyResultDataAccessException(1));
			return pessoaSalva;
		}
		

	
	public Pessoa atualizar(Long codigo,Pessoa pessoa) {
	    Pessoa pessoaSalva = buscaPeloCodigoPessoa(codigo);
	   
	    BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
	    return pessoaRepository.save(pessoaSalva);
	}
	


	public void atualizarPropiedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscaPeloCodigoPessoa(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
		
	}
	
	
}