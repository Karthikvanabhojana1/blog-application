package com.karthik.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryservice;

	// create update
	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO catdto = this.categoryservice.createUser(categoryDTO);
		return new ResponseEntity<CategoryDTO>(catdto, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer id) {
		CategoryDTO updatedCategory = this.categoryservice.updateUser(categoryDTO,id);
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<APIResponse> deleteCategory( @PathVariable Integer id) {
	this.categoryservice.delete(id);		
		return new ResponseEntity<APIResponse>(new APIResponse("Category Deleted Sucessfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/getcatagories")
	public ResponseEntity<List<CategoryDTO>> getAllUsers() {
		return ResponseEntity.ok(this.categoryservice.getAll());
	}


	@GetMapping("/getbyId/{id}")
	public ResponseEntity<CategoryDTO> getByID(@PathVariable Integer id) {
		CategoryDTO foundcategory = this.categoryservice.getuserById(id);
		return new ResponseEntity<CategoryDTO>(foundcategory, HttpStatus.OK);
	}

}
