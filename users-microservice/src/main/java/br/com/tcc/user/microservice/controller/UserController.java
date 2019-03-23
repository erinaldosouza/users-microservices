package br.com.tcc.user.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.to.impl.UserTO;

//TODO change the System.out.println for a logger;
@RestController
public class UserController {
	
	private final UserService<UserTO> userService;	
	private final EurekaClient eurekaClient;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UserController(UserService<UserTO> userService, EurekaClient eurekaClient) {
		this.userService = userService;
		this.eurekaClient = eurekaClient;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody UserTO user) {
		System.out.println("Post request to /save: " + user);
		this.userService.save(user);		
	}
	
	@GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void find(@PathVariable(value="id", required=true) Long id) {
		System.out.println("Get request to id: " + id);

		this.userService.find(id);
    }
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void findAll() {
		System.out.println("Get request to find all");

		 InstanceInfo instance = eurekaClient.getNextServerFromEureka(this.appName, false);
		 
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		 HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
	
		 new RestTemplate().getForObject("http://127.0.0.1:52587/" + instance.getAppName().toLowerCase() +"/", Void.class,  entity);
		 this.userService.findAll();
	}
	
	@PutMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable(value="id", required=true) Long id, @RequestBody(required=true) UserTO user) {
		System.out.println("Put request with id: " + id + " and body: " + user);
		user.setId(id);
		this.userService.update(user);
	} 
	
	@DeleteMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable(value="id", required=true) Long id) {
		System.out.println("Detele request with id: " + id);
		this.userService.delete(id);
	}

}
