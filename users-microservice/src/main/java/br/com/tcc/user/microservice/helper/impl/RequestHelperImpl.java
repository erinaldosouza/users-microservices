package br.com.tcc.user.microservice.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.tcc.user.microservice.helper.IRequestHelper;
import br.com.tcc.user.microservice.model.impl.User;
import br.com.tcc.user.microservice.wrapper.UserWrapper;

@Component
public class RequestHelperImpl implements IRequestHelper<UserWrapper, User> {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public RequestHelperImpl (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private HttpHeaders getHeaders(MediaType mediaType, String apikey) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(mediaType);
		httpHeaders.add("api-key", apikey);
		return httpHeaders;
	}
		
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method, User body, HttpHeaders httpHeaders) {
		
		MultiValueMap<String, Object> map = null;
		
		if(body != null) {
			map = new LinkedMultiValueMap<>();
			map.add("login", body.getLogin());
			map.add("password", body.getPassword());
			if(body.getDocument() != null) {
				map.set("document", body.getDocument().getResource());				
			}
		}
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
		ResponseEntity<UserWrapper> response = restTemplate
				                    .exchange(url, method, entity, new ParameterizedTypeReference<UserWrapper>(){});

		return response;
	}
	
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method, User body, String apikey) {
		return doRequestDefault(url, method, body,  getHeaders(MediaType.APPLICATION_JSON, apikey));
	}
	
	@Override
	public ResponseEntity<UserWrapper> doGet(String url, String apikey) {
		return this.doRequestDefault(url, HttpMethod.GET, null, getHeaders(MediaType.APPLICATION_JSON, apikey));		
	}

	@Override
	public ResponseEntity<UserWrapper> doPost(String url, User body, String apikey) {
		return this.doRequestDefault(url, HttpMethod.POST, body, getHeaders(MediaType.MULTIPART_FORM_DATA, apikey));
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPut(String url, User body, String apikey) {
		return this.doRequestDefault(url, HttpMethod.PUT, body,  getHeaders(MediaType.MULTIPART_FORM_DATA, apikey));
	}
			
	@Override
	public ResponseEntity<UserWrapper> doDelete(String url, String apikey) {
		return this.doRequestDefault(url, HttpMethod.DELETE,  null, getHeaders(MediaType.APPLICATION_JSON, apikey));
	}
	

	@Override
	public ResponseEntity<byte[]> doGetBinary(String url, String apikey) {
		return doRequestDefaultBinary(url, HttpMethod.GET, getHeaders(MediaType.APPLICATION_JSON, apikey));
	}

	@Override
	public ResponseEntity<byte[]> doRequestDefaultBinary(String url, HttpMethod method, HttpHeaders getHeaders) {
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(getHeaders);
		ResponseEntity<byte[]> response = restTemplate
				                         .exchange(url, method, entity, byte[].class);
		return response;
	}

}
