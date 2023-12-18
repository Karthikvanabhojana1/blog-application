package com.karthik.blog.controllers;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.payloads.APIResponse;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userservice;	
	//Post-create User	
	@PostMapping("/create")
	public ResponseEntity<UserDTO>  createUser(@RequestBody UserDTO userdto){
		
		UserDTO createdUserDTO= this.userservice.createUser(userdto);
		
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
		
	}	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO>  updateUser(@RequestBody UserDTO userdto, @PathVariable("id") int id){
		
		UserDTO udto=this.userservice.updateUser(userdto, id);
		
		
		return new ResponseEntity<>(udto,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<APIResponse> deleteuser(@PathVariable("id") int id){
		
		this.userservice.delete(id);
		
		
		return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Sucessfully",true),HttpStatus.OK);
		
	}

	@GetMapping("/getusers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		
		
		return ResponseEntity.ok(this.userservice.getAll());
	}
	@GetMapping("/getusers/{id}")

public ResponseEntity<UserDTO> getAllUserbyId(@PathVariable("id") int id){
		
		
		return ResponseEntity.ok(this.userservice.getuserById(id));
	}
	
}
