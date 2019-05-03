package br.com.tcc.user.microservice.configuration.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizerResponseTO implements Serializable {

	private static final long serialVersionUID = -1816922576813611343L;
	
	private boolean success;
	private String message;
	
	@JsonProperty("request_status")
	private int requestStatus;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	

}
