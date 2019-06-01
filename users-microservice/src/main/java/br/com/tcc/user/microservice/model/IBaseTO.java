package br.com.tcc.user.microservice.model;

import java.io.Serializable;

public interface IBaseTO< T extends Serializable> extends Serializable {

	T getId();
	void setId(T t);
}
