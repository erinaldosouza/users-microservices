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
	
	private String idPhoto;
	
	private MultipartFile document;
	
	private String base64Photo;

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

	public MultipartFile getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) {
		this.document = document;
	}

	public String getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getBase64Photo() {
		return base64Photo;
	}

	public void setBase64Photo(String base64Photo) {
		this.base64Photo = base64Photo;
	}
}
