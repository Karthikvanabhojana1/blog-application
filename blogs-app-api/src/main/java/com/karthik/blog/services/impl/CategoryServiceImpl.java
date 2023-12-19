package com.karthik.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.repositories.CategoryRepository;
import com.karthik.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDTO createUser(CategoryDTO category) {
		// TODO Auto-generated method stub

		Categories categories = this.modelmapper.map(category, Categories.class);
		Categories addedcategorie = this.categoryRepo.save(categories);

		return this.modelmapper.map(addedcategorie, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateUser(CategoryDTO category, Integer categoryid) {
		// TODO Auto-generated method stub
		Categories categories = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryid));

		categories.setCategoryDetails(category.getCategoryDetails());
		categories.setCategoryTitle(category.getCategoryTitle());
		Categories updated = this.categoryRepo.save(categories);

		return this.modelmapper.map(updated, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getuserById(Integer categoryid) {

		Categories categories = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryid));

		return this.modelmapper.map(categories, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAll() {
		List<Categories> listofcategories = this.categoryRepo.findAll();
		List<CategoryDTO> listofcategoriesDTO = listofcategories.stream()
				.map((cat) -> this.modelmapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

		return listofcategoriesDTO;

	}

	@Override
	public void delete(Integer categoryid) {
		Categories categories = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryid));

		this.categoryRepo.delete(categories);
	}
	
	

}
