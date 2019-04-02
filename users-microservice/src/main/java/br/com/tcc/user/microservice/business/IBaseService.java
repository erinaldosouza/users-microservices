package br.com.tcc.user.microservice.business;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

public interface IBaseService<T, E>  {

	ResponseEntity<T> save (E t);

	ResponseEntity<T> find (Serializable id);
	
	ResponseEntity<T> findAll();

	ResponseEntity<T> update (E t);	
	
	ResponseEntity<T> delete (Serializable id);	
}
