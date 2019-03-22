package br.com.tcc.user.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.to.impl.UserTO;

//TODO change the System.out.println for a logger;
@RestController
@RequestMapping("user")
public class UserController {
	
	private UserService<UserTO> userService;
	
	@Autowired
	public UserController(UserService<UserTO> userService) {
		this.userService = userService;
	}
	
	@PostMapping(value="save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody UserTO user) {
		System.out.println("Post request to /save: " + user);
		this.userService.save(user);		
	}
	
	@GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void find(@PathVariable(value="id", required=true) Long id) {
		System.out.println("Get request to id: " + id);

		this.userService.find(id);
    }
	
	@GetMapping(value="all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void findAll() {
		System.out.println("Get request to find all");
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
