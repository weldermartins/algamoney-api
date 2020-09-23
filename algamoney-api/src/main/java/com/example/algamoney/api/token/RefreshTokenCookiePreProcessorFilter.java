package com.example.algamoney.api.token;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//pre processo antes de inseir
@Component
//filtro com prioridade alta
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//pegar o http servlet request.
		HttpServletRequest req = (HttpServletRequest)request;
		//verifica se a requição é para oauth/token
		if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equals(req.getParameter("grant_type"))
				&& req.getCookies()!=null) {
			String refreshToken = 
			        Stream.of(req.getCookies())
			            .filter(cookie -> "refreshToken".equals(cookie.getName()))
			            .findFirst()
			            .map(cookie -> cookie.getValue())
			            .orElse(null);

		    req = new MyservletRequestWrapper(req, refreshToken);
			
		}
		chain.doFilter(req, response);
		
	}
	
	static class MyservletRequestWrapper extends HttpServletRequestWrapper{
       private String  refreshToken;
		public MyservletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
			
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] { refreshToken });
			map.setLocked(true);
			return map;
		}
		
	}
	
	
	
	
	

}
