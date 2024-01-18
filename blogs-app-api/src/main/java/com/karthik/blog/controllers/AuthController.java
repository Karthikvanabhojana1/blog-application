package com.karthik.blog.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.payloads.JwtAuthRequest;
import com.karthik.blog.security.CustomUserDetailsService;
import com.karthik.blog.security.JwtAuthResponse;
import com.karthik.blog.security.JwtAuthenticationEntryPoint;
import com.karthik.blog.security.JwtAuthenticationFilter;
import com.karthik.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/init/api/v1/auth/")
public class AuthController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/login")

	
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
	    try {
			this.authenticate(request.getUsername(), request.getPassword());
	        String token = this.jwtTokenHelper.generateToken(this.userDetailsService.loadUserByUsername(request.getUsername()));
	        JwtAuthResponse response = new JwtAuthResponse();
	        response.setToken(token);
	        response.setResponse("SUCCESS");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (AuthenticationException e) {
	        JwtAuthResponse response = new JwtAuthResponse();
	        response.setToken("");
	        response.setResponse("Invalid UserName or Password");
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    }
	}


	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new 
				UsernamePasswordAuthenticationToken(username, password);
	this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		
	}
}
