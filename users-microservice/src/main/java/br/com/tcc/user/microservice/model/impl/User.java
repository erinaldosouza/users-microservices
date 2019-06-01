package br.com.tcc.user.microservice.model.impl;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.tcc.user.microservice.model.IBaseTO;
	
@JsonInclude(Include.NON_NULL)
public class User implements IBaseTO<Long> {
	
	private static final long serialVersionUID = 139399624282336753L;

	private Long id;
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String password;
	
	private MultipartFile photo;

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

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
}
