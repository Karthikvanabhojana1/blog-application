package com.karthik.blog.services;

import java.util.List;

import com.karthik.blog.payloads.CategoryDTO;



public interface CategoryService {

	
	CategoryDTO createUser(CategoryDTO category) ;
	CategoryDTO updateUser(CategoryDTO category,Integer categoryid) ;
	CategoryDTO getcategoryById(Integer categoryid);
	List<CategoryDTO> getAll();
	void delete(Integer categoryid);

}
