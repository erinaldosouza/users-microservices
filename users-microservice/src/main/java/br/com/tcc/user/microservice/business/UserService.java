package br.com.tcc.user.microservice.business;

import br.com.tcc.user.microservice.to.impl.UserTO;
import br.com.tcc.user.microservice.wrapper.UserWrapper;

public interface UserService extends IBaseService<UserWrapper, UserTO> {

}
