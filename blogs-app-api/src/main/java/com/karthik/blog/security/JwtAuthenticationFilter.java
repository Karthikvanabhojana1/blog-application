package com.karthik.blog.security;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//token		
		
		String requestToken=request.getHeader("Authorization");
		String username=null;
		String token=null;
		 if (requestToken != null && requestToken.startsWith("Bearer")) {
	            token = requestToken.substring(7);
	            try {

	                username = this.jwtTokenHelper.getUsernameFromToken(token);

	            } catch (IllegalArgumentException e) {
	                logger.info("Illegal Argument while fetching the username !!");
	                e.printStackTrace();
	            } catch (ExpiredJwtException e) {
	                logger.info("Given jwt token is expired !!");
	                e.printStackTrace();
	            } catch (MalformedJwtException e) {
	                logger.info("Some changed has done in token !! Invalid Token");
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();

	            }


	        } else {
	            logger.info("Invalid Header Value !! ");
	        }


	        //
 	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            Boolean validateToken = this.jwtTokenHelper.validateToken(token, userDetails);
	            if (validateToken) {

	                //set the authentication
	            	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);


	            } else {
	                logger.info("Validation fails !!");
	            }


	        }

	        filterChain.doFilter(request, response);


	    }

}
