package br.com.tcc.user.microservice.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.tcc.user.microservice.model.impl.User;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class UserWrapper implements Serializable {

	private static final long serialVersionUID = -4887772344155987297L;
	
	private User user;
	private Long userId;
	private List<User> users;
	
	private String error;
	private String message;
	
	public UserWrapper(Long userId) {
		this.userId = userId;
	}
	
	public UserWrapper() {
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
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
