package com.example.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

// criando um evento da aplicação.
// é necessário criar um serial versionid e o construtor
public class RecursoCriadoEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long codigo;
   
	//obtendo a resposta e o código
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		
		this.response = response;
		this.codigo = codigo;
		
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	
	public Long getCodigo() {
		return codigo;
	}

	
	
	
	
	

}
