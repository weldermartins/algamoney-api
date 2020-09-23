package com.example.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

//faz parte do spring boot
@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private String OriginPermitida = "http://localhost:8000";
			
	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	//relacionado a segurança
	public static class Seguranca{
		
		// configuração de propriedade
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}		
		
	}
	public String getOriginPermitida() {
		return OriginPermitida;
	}
	public void setOriginPermitida(String originPermitida) {
		OriginPermitida = originPermitida;
	}
	
	
}
