package br.com.tcc.user.microservice.business;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.tcc.user.microservice.to.IBaseTO;

public interface IBaseService<T extends IBaseTO<? extends Serializable >>  {

	ResponseEntity<T> save (T t);

	T find (Serializable id);
	ResponseEntity<List<T>> findAll();

	T update (T t);	
	
	void delete (Serializable id);	
}
