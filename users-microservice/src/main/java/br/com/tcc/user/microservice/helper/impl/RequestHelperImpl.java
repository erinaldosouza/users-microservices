package br.com.tcc.user.microservice.helper.impl;

import java.util.Map;

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
import br.com.tcc.user.microservice.wrapper.UserWrapper;

@Component
public class RequestHelperImpl implements IRequestHelper<UserWrapper> {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public RequestHelperImpl (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private HttpHeaders getHeaders(Map<String, String> headers) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		if(headers != null) {
			headers.entrySet().forEach( e -> {
				httpHeaders.add(e.getKey(), e.getValue());
			});
		}

		return httpHeaders;
	}
		
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method, Map<String, Object> body, Map<String, String> headers) {
		
		HttpHeaders httpHeaders = getHeaders(headers);		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		if(body != null) {
			body.entrySet().forEach(e -> {
				map.add(e.getKey(),  e.getValue());
			});
		}
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
		ResponseEntity<UserWrapper> response = restTemplate
				                    .exchange(url, method, entity, new ParameterizedTypeReference<UserWrapper>(){});

		return response;
	}
	
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method) {
		return doRequestDefault(url, method, null, null);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doGet(String url) {
		return this.doRequestDefault(url, HttpMethod.GET);		
	}
	
	@Override
	public ResponseEntity<UserWrapper> doGet(String url, Map<String, String> headers) {
		return this.doRequestDefault(url, HttpMethod.GET);		
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPost(String url, Map<String, Object> body) {
		return this.doRequestDefault(url, HttpMethod.POST, body, null);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPost(String url, Map<String, Object> body, Map<String, String> headers) {
		return this.doRequestDefault(url, HttpMethod.POST, body, headers);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPut(String url, Map<String, Object> body) {
		return this.doRequestDefault(url, HttpMethod.PUT, body, null);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPut(String url, Map<String, Object> body, Map<String, String> headers) {
		return this.doRequestDefault(url, HttpMethod.PUT, body, headers);
	}
		
	@Override
	public ResponseEntity<UserWrapper> doDelete(String url) {
		return this.doRequestDefault(url, HttpMethod.DELETE);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doDelete(String url, Map<String, String> headers) {
		return this.doRequestDefault(url, HttpMethod.DELETE, null, headers);
	}

	@Override
	public ResponseEntity<byte[]> doGetBinary(String url) {
		return doRequestDefaultBinary(url, HttpMethod.GET, null);
	}

	@Override
	public ResponseEntity<byte[]> doGetBinary(String url, Map<String, String> headers) {
		return doRequestDefaultBinary(url, HttpMethod.GET, headers);
	}

	@Override
	public ResponseEntity<byte[]> doRequestDefaultBinary(String url, HttpMethod method, Map<String, String> headers) {
		HttpHeaders httpHeaders = getHeaders(headers);		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
		ResponseEntity<byte[]> response = restTemplate
				                       .exchange(url, method, entity, byte[].class);

		return response;
	}

}
