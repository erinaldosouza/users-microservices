package br.com.tcc.user.microservice.business;

import java.io.Serializable;

import br.com.tcc.user.microservice.to.IBaseTO;

public interface UserService<T extends IBaseTO<? extends Serializable>> extends IBaseService<T> {

}
