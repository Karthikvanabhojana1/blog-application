package com.karthik.blog.payloads;

import com.karthik.blog.entities.Categories;

public class CategoryTransformer {
	


	    public static CategoryDTO convertToDTO(Categories category) {
	        CategoryDTO categoryDTO = new CategoryDTO();
	        categoryDTO.setCategoryId(category.getCategoryId());
	        categoryDTO.setCategoryTitle(category.getCategoryTitle());
	        categoryDTO.setCategoryDetails(category.getCategoryDetails());
	        	        return categoryDTO;
	    }

	    public static Categories convertToEntity(CategoryDTO categoryDTO) {
	        Categories category = new Categories();
	        category.setCategoryId(categoryDTO.getCategoryId());
	        category.setCategoryTitle(categoryDTO.getCategoryTitle());
	        category.setCategoryDetails(categoryDTO.getCategoryDetails());
	        // Add other mapping logic if needed
	        return category;
	    	}

}
