package br.com.tcc.user.microservice.to.impl;

import br.com.tcc.user.microservice.to.IBaseTO;

public class UserTO implements IBaseTO<Long> {
	
	private Long id;
	
	private String login;
	
	private String password;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
