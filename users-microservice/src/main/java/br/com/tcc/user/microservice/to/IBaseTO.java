package br.com.tcc.user.microservice.to;

import java.io.Serializable;

public interface IBaseTO< T extends Serializable> {

	T getId();
	void setId(T t);
}
