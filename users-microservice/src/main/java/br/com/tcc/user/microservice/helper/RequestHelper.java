package br.com.tcc.user.microservice.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHelper<T> {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public RequestHelper (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private HttpEntity<String> getDefaultJsonHeaders() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
       
		HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
		
		return entity;
	}
	
	private ResponseEntity<T> doRequestDefault(String url, HttpMethod method, Object... body) {
		HttpEntity<String> entity = this.getDefaultJsonHeaders();		
		ResponseEntity<T> response = restTemplate
				                     .exchange(url, method, entity, new ParameterizedTypeReference<T>(){});
		return response;
		
	}
	
	public ResponseEntity<T> doGet(String url) {
		return this.doRequestDefault(url, HttpMethod.GET);		
	}
	
	public ResponseEntity<T> doPost(String url, Object body) {
		return this.doRequestDefault(url, HttpMethod.POST, body);
	}
	
	public ResponseEntity<T>  doPut(String url, Object body) {
		return this.doRequestDefault(url, HttpMethod.PUT, body);
	}
		
	public ResponseEntity<T> doDelete(String url) {
		return this.doRequestDefault(url, HttpMethod.DELETE);
	}
}
