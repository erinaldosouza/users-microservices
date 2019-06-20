package br.com.tcc.user.microservice.business.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.helper.IRequestHelper;
import br.com.tcc.user.microservice.model.impl.User;
import br.com.tcc.user.microservice.wrapper.UserWrapper;

@Service
@DefaultProperties(defaultFallback="fallback")
public class UserServiceImpl implements UserService {

	@Value("${application.user.persistence.service}")
	private String userPersistenceService;
	
	@Value("${application.services.apikey.name}")
	private String apikeyname;
		
	private final EurekaClient eurekaClient;		
	private final IRequestHelper<UserWrapper, User> requestHelper;
	
	@Autowired
	public UserServiceImpl (EurekaClient eurekaClient, IRequestHelper<UserWrapper, User> requestHelper) {
		this.eurekaClient = eurekaClient;
		this.requestHelper = requestHelper;
	}
	
	@Override
	@HystrixCommand
	public ResponseEntity<UserWrapper> save(User user) {

		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);		
		return requestHelper.doPost(instanceInfo.getHomePageUrl(), user, instanceInfo.getMetadata().get(apikeyname));		
	}

	@Override
	@HystrixCommand
	public ResponseEntity<UserWrapper> find(Serializable id) {

		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);
		return requestHelper.doGet(instanceInfo.getHomePageUrl() + id, instanceInfo.getMetadata().get(apikeyname));		
	}

	@Override
	@HystrixCommand
	(commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")
	})
	public ResponseEntity<UserWrapper> findAll() {

		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);
		return requestHelper.doGet(instanceInfo.getHomePageUrl(), instanceInfo.getMetadata().get(apikeyname));
	}

	@Override
	@HystrixCommand
	public ResponseEntity<UserWrapper> update(User user) {

		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);
		return requestHelper.doPut(instanceInfo.getHomePageUrl() + user.getId(), user, instanceInfo.getMetadata().get(apikeyname));
	}

	@Override
	@HystrixCommand
	public ResponseEntity<UserWrapper> delete(Serializable id) {

		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(userPersistenceService, Boolean.FALSE);
		return requestHelper.doDelete(instanceInfo.getHomePageUrl() + id, instanceInfo.getMetadata().get(apikeyname));		
	}
	
	public ResponseEntity<UserWrapper> fallback(Throwable exception) {
		
		exception.printStackTrace();
		UserWrapper wrapper = new UserWrapper();
		
		wrapper.setMessage("It wasn't possible to execute your request. Please, try again soon.");
		StringBuilder error = new StringBuilder();
		
		for(Throwable e : exception.getSuppressed()) {
			error.append(e.getMessage()).append("\\n");
		}
		
		error.append(exception.toString());
		wrapper.setError(error.toString());
		
		int errorCode = 500;
		
		try {
		
			errorCode = Integer.valueOf(exception.getMessage().replaceAll("\\D", ""));
		
		} catch (Exception e) {
			// nothing helpfull to print herer e.printStackTrace();
		}
		
		return ResponseEntity.status(errorCode).body(wrapper);
	}
}
