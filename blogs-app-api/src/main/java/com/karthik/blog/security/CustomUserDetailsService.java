package com.karthik.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.User;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.repositories.UserRepositories;
@Service
public class CustomUserDetailsService implements UserDetailsService {
@Autowired
	private UserRepositories userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userrepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Email", " Email Id", username));

		//load user from user database
		return user;
	}
  

}
