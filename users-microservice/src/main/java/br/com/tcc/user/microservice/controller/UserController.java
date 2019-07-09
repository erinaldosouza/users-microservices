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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping(value="/user")
	public  ResponseEntity<UserWrapper> save(@RequestPart(name="document", required=false) MultipartFile document,  @Valid User user) throws IOException {
		user.setDocument(document);
		return this.userService.save(user);		
	}
	
	@GetMapping(value="/user/{id}")
	public ResponseEntity<UserWrapper> find(@PathVariable(name="id", required=true) Long id) {
		return this.userService.find(id);
    }
	
	@GetMapping(value="/user")
	public ResponseEntity<UserWrapper> findAll() {
		 return this.userService.findAll();
	}
	
	@PutMapping(value="/user/{id}")
	public ResponseEntity<UserWrapper> update(@PathVariable(name="id", required=true) Long id, @RequestPart(name="document", required=false) MultipartFile document,  @Valid User user) {
		user.setId(id);
		user.setDocument(document);
		return this.userService.update(user);
	} 
	
	@DeleteMapping(value="/user/{id}")
	public ResponseEntity<UserWrapper> delete(@PathVariable(name="id", required=true) Long id) {
		return this.userService.delete(id);
	}
	
	@GetMapping(value="api/user/version")
	public ResponseEntity<String> find() {
		return ResponseEntity.ok("1.0.beta");
    }
}
