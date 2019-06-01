package br.com.tcc.user.microservice.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.model.impl.User;
import br.com.tcc.user.microservice.wrapper.UserWrapper;

//TODO change the System.out.println for a logger;
@RestController
public class UserController {
	
	private final UserService userService;	
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value="/")
	public  ResponseEntity<UserWrapper> save(@Valid User user) throws IOException {
		System.out.println("Post request to /save: " + user);
		return this.userService.save(user);		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserWrapper> find(@PathVariable(value="id", required=true) Long id) {
		System.out.println("Get request to id: " + id);
		return this.userService.find(id);
    }
	
	@GetMapping(value="/")
	public ResponseEntity<UserWrapper> findAll() {
		 return this.userService.findAll();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<UserWrapper> update(@PathVariable(value="id", required=true) Long id, @Valid User user) {
		user.setId(id);
		System.out.println("Put request with id: " + id + " and body: " + user);
		return this.userService.update(user);
	} 
	
	@DeleteMapping(value="/{id}")
	public  ResponseEntity<String> delete(@PathVariable(value="id", required=true) Long id) {
		System.out.println("Detele request with id: " + id);
		this.userService.delete(id);
		return ResponseEntity.ok().build();
	}
}
