package com.karthik.blog.payloads;

import java.util.Date;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.User;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private Integer postId;
	@Size(max = 100, message = "Title should not exceed 100 characters !!")
	private String postTitle;
	private String imageName;
	@Size(max = 10000, message = "Content should not exceed 10000 characters !!")
	private String content;
	private Date addedDate;
	private CategoryDTO categories;
	private UserDTO user;
}
