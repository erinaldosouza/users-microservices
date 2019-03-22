package br.com.tcc.user.microservice.business;

import java.io.Serializable;
import java.util.List;

import br.com.tcc.user.microservice.to.IBaseTO;

public interface IBaseService<T extends IBaseTO<? extends Serializable >>  {

	T save (T t);

	T find (Serializable id);
	List<T> findAll();

	T update (T t);	
	
	void delete (Serializable id);	
}
