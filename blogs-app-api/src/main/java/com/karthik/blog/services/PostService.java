package com.karthik.blog.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karthik.blog.entities.Post;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;
import com.karthik.blog.payloads.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO postDTO,Integer categoryId,Integer userId,String path, MultipartFile file) throws IOException;
	PostDTO updatePost(PostDTO postDTO,Integer postId);
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDirection);
	
	PostDTO getpostbyId(Integer postId);
	PostResponse getpostbyCategory(Integer categoryId,int pageNumber, int pageSize, String sortBy, String sortDirection);

	PostResponse getpostbyUser(Integer userId,int pageNumber, int pageSize, String sortBy, String sortDirection);
	PostResponse searchPost(String keywords,int pageNumber, int pageSize, String sortBy, String sortDirection);
	void delete(Integer postId);
	List<PostDTO> searchTitle(String keyword);
}
