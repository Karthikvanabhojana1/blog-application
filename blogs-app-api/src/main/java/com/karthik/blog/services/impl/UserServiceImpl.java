package com.karthik.blog.services.impl;

import java.util.List;



import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.User;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.repositories.UserRepositories;
import com.karthik.blog.services.UserService;
import com.karthik.blog.exception.*;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private UserRepositories userrepositories;


    UserServiceImpl(ModelMapper modelmapper) {
        this.modelmapper = modelmapper;
    }
	
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user= this.dtotoUser(userDto);
		
		User saveduser=this.userrepositories.save(user);
		return this.Usertodto(saveduser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer id) {
		// TODO Auto-generated method stub
		
		
		User user=this.userrepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", " Id", id));
		
		user.setAbout(userdto.getAbout());
		user.setCity(userdto.getCity());
		user.setCountry(userdto.getCountry());
		user.setEmail(userdto.getEmail());
		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setPassword(userdto.getPassword());
		user.setProvices(userdto.getProvices());
		user.setStreetAddress(userdto.getStreetAddress());
		user.setUserName(userdto.getUserName());
		user.setZipCode(userdto.getZipCode());
		
		
		User updateUser=this.userrepositories.save(user)	;
		UserDTO userdto1=this.Usertodto(updateUser);

		
		return userdto1;
	}

	@Override
	public UserDTO getuserById(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userrepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", " Id", id));

		return this.Usertodto(user);
	}

	@Override
	public List<UserDTO> getAll() {		
		List<User> listofuser=this.userrepositories.findAll();		
		List<UserDTO>listUserDTO=listofuser.stream().map(user->this.Usertodto(user)).collect(Collectors.toList());
		return listUserDTO;
	}

	@Override
	public void delete(Integer id) {
		User user=this.userrepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", " Id", id));
this.userrepositories.delete(user);
	}
	
	private User dtotoUser(UserDTO userdto) {
				User user=this.modelmapper.map(userdto, User.class); 
		return user;
	}
	
	
private UserDTO Usertodto(User user) {
		UserDTO useruserDTO= this.modelmapper.map(user, UserDTO.class);
		return useruserDTO;
	}


@Override
public boolean isEmailUnique(String email) {
    return userrepositories.findByEmail(email).isEmpty();
}
}
