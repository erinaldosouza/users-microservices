package br.com.tcc.user.microservice.business.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.to.impl.UserTO;

@Component
public class UserServiceImpl implements UserService<UserTO> {

	@Value("${application.user.persistence.service}")
	private String userPersistenceService;
	
	private final EurekaClient eurekaClient;
	
	@Autowired
	public UserServiceImpl (EurekaClient eurekaClient) {
		this.eurekaClient = eurekaClient;
	}
	
	@Override
	public ResponseEntity<UserTO> save(UserTO t) {		
		return null;
	}

	@Override
	public UserTO find(Serializable t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<UserTO>> findAll() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
       
		HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);
		ResponseEntity<List<UserTO>> response =
				                     restTemplate
				                     .exchange(instanceInfo.getHomePageUrl(),  
				                                HttpMethod.GET,  entity, new ParameterizedTypeReference<List<UserTO>>(){});
		return response;
	}

	@Override
	public UserTO update(UserTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
	}

}
