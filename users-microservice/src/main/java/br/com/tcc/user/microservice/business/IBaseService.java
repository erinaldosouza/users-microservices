package br.com.tcc.user.microservice.business;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

public interface IBaseService<T, E>  {

	ResponseEntity<T> save (E e);

	ResponseEntity<T> find (Serializable id);
	
	ResponseEntity<T> findAll();

	ResponseEntity<T> update (E e);	
	
	ResponseEntity<T> delete (Serializable id);	
}
