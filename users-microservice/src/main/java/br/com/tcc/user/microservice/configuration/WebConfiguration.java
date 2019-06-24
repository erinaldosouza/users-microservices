package br.com.tcc.user.microservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfiguration {
	
	@Bean
	protected RestTemplate getRestTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(5000);
		
		// Read timeout
		// clientHttpRequestFactory.setReadTimeout(0);
		return clientHttpRequestFactory;
	}

}
