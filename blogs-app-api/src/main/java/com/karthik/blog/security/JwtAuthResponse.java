package com.karthik.blog.security;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	private String response;
}
