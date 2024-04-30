package com.EmailApi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailResponse {
	
	@JsonProperty
	private String token;
	
	public EmailResponse(String token) {
		this.token= token;
	}
	
}
