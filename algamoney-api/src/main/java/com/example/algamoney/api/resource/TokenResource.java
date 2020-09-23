package com.example.algamoney.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	//Excluir o token da mémoria ou invalidar
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		//nome do token na aplicação refreshToken
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		
		//cookie.setSecure(false);estará como false quando não estiver em produção
		cookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps());
		
		cookie.setPath(req.getContextPath() + "/oauth/token");
		//expirar agora.
		cookie.setMaxAge(0);
		//requisição adicionar o cookie
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
		
		
		
	}

}
