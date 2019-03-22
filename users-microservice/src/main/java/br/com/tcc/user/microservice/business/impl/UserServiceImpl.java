package br.com.tcc.user.microservice.business.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.tcc.user.microservice.business.UserService;
import br.com.tcc.user.microservice.to.impl.UserTO;

@Component
public class UserServiceImpl implements UserService<UserTO> {

	@Override
	public UserTO save(UserTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTO find(Serializable t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTO update(UserTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
	}

}
