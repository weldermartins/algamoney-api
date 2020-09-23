package com.example.algamoney.api.repository.projection;

public class ResumoFornecedor {
	
	private Long codigo;
	private String nome;
	private String categoria;
	
	
	
	public ResumoFornecedor(Long codigo, String nome, String categoria) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	

}
