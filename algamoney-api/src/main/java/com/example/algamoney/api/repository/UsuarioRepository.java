package com.example.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//Optiona ajuda a não ficar criando condições se não encontrar. 
	public Optional<Usuario> findByEmail(String email);
	
	
	

}
