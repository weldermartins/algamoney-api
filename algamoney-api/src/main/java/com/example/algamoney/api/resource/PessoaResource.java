package com.example.algamoney.api.resource;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	//publicador de eventos da aplicação
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	
	
	@PutMapping("/{codigo}")
	Pessoa atualizar(@PathVariable Long codigo, @RequestBody Pessoa pessoa) {
	   	Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);   
	    return pessoaRepository.save(pessoaSalva);	    
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropiedadeAtivo(codigo,ativo);	
		
	}
	
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')and #oauth2.hasScope('read')")
	public List<Pessoa> listar(){
		
		return pessoaRepository.findAll(); 
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA')and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.CREATED)
	//Com a URI retorna o link depois de ser inserido pelo código - http://localhost:8080/pessoas/13
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
		return pessoa.isPresent()? ResponseEntity.ok(pessoa.get()): ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthoriry")
	//Apos a exclusão retornar o codigo 204
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void remover(@PathVariable Long codigo) {
		
		this.pessoaRepository.deleteById(codigo);
		
	}
	
	
	
	
	

}
