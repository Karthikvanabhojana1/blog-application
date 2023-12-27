package com.karthik.blog.services;

import java.util.List;

import com.karthik.blog.entities.Post;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;

public interface PostService {
	PostDTO createPost(PostDTO postDTO,Integer categoryId,Integer userId);
	PostDTO updatePost(PostDTO postDTO,Integer postId);
	List<PostDTO> getAllPosts();
	
	PostDTO getpostbyId(Integer postId);
	List<PostDTO> getpostbyCategory(Integer categoryId);

	List<PostDTO> getpostbyUser(Integer userId);
	List<PostDTO> serarchPost(String keywords);
	void delete(Integer postId);
}
