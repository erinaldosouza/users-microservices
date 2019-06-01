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
		
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method, User body) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		if(body != null) {
			map.add("login", body.getLogin());
			try {
				map.add("photo",  body.getPhoto().getResource());
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.add("password", body.getPassword());
		}
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
		ResponseEntity<UserWrapper> response = restTemplate
				                    .exchange(url, method, entity, new ParameterizedTypeReference<UserWrapper>(){});
		return response;
	}
	
	@Override
	public ResponseEntity<UserWrapper> doRequestDefault(String url, HttpMethod method) {
		return doRequestDefault(url, method, null);
	}
	
	@Override
	public ResponseEntity<UserWrapper> doGet(String url) {
		return this.doRequestDefault(url, HttpMethod.GET);		
	}
	
	@Override
	public ResponseEntity<UserWrapper> doPost(String url, User body) {
		return this.doRequestDefault(url, HttpMethod.POST, body);
	}
	
	@Override
	public ResponseEntity<UserWrapper>  doPut(String url, User body) {
		return this.doRequestDefault(url, HttpMethod.PUT, body);
	}
		
	@Override
	public ResponseEntity<UserWrapper> doDelete(String url) {
		return this.doRequestDefault(url, HttpMethod.DELETE);
	}
}
