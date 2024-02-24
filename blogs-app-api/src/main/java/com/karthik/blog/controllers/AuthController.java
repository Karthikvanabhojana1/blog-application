package com.karthik.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.payloads.JwtAuthRequest;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.security.JwtAuthResponse;
import com.karthik.blog.security.JwtTokenHelper;
import com.karthik.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/init/api/v1/auth/")
public class AuthController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userservice;

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


	@PostMapping("/register")
		public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto) {
		UserDTO registeredUser= this.userservice.registerUser(userdto);
			return new ResponseEntity<>(this.userservice.createUser(registeredUser), HttpStatus.CREATED);
	
		}
}
