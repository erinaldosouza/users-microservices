package br.com.tcc.user.microservice.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.tcc.user.microservice.to.impl.UserTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class UserWrapper implements Serializable {

	private static final long serialVersionUID = -4887772344155987297L;
	
	private UserTO user;
	private List<UserTO> users;
	
	private String error;
	private String message;

	public UserTO getUser() {
		return user;
	}
	public void setUser(UserTO user) {
		this.user = user;
	}
	public List<UserTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserTO> users) {
		this.users = users;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String errorType) {
		this.error = errorType;
	}
}
