package com.karthik.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.Comment;
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
	@Size(min=1, max = 100, message = "Title should not exceed 100 characters !!")
	private String postTitle;
	private String imageName;
	@Size(min=5, max = 10000, message = "Content should not exceed 10000 characters !!")
	private String content;
	private Date addedDate;
	private Categories categories;
	private User user;
	private List<Comment> comments=new ArrayList<>();

}

