package com.karthik.blog.services;

import java.util.List;

import com.karthik.blog.payloads.UserDTO;

public interface UserService {

	UserDTO registerUser(UserDTO user) ;
	UserDTO createUser(UserDTO user) ;
	UserDTO updateUser(UserDTO user,Integer id) ;
	UserDTO getuserById(Integer id);
	List<UserDTO> getAll();
	void delete(Integer id);
	boolean isEmailUnique(String email);

}
