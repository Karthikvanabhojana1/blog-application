package com.karthik.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.entities.Post;
import com.karthik.blog.payloads.APIResponse;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;
import com.karthik.blog.payloads.PostResponse;
import com.karthik.blog.services.PostService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api")
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO createPostDTO = this.postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<>(createPostDTO, HttpStatus.CREATED);

	}

	@GetMapping("post/getallposts")
	public ResponseEntity<PostResponse> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "postId",required = false) String sortDirection

			)
		 {
		
		
		return ResponseEntity.ok(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection));
	}

	@GetMapping("/post/category/{categoryId}")
	public ResponseEntity<PostResponse> getpostbycategory(@PathVariable Integer categoryId,	@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "postId",required = false) String sortDirection
) {
		return ResponseEntity.ok(this.postService.getpostbyCategory(categoryId,pageNumber,pageSize,sortBy,sortDirection));
	}

	@GetMapping("/post/user/{userId}")
	public ResponseEntity<PostResponse> getpostbyuser(@PathVariable Integer userId,	@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "postId",required = false) String sortDirection
) {
		return ResponseEntity.ok(this.postService.getpostbyUser(userId,pageNumber,pageSize,sortBy,sortDirection));

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getpostbyId(@PathVariable Integer postId) {
		return ResponseEntity.ok(this.postService.getpostbyId(postId));

	}
	
	@PutMapping("/post/update/{postId}")
	public ResponseEntity<PostDTO> updatepost(@Valid @RequestBody PostDTO postDto, @PathVariable Integer postId) {
		return ResponseEntity.ok(this.postService.updatePost(postDto,postId));

	}
	@DeleteMapping("/post/delete/{id}")
	public ResponseEntity<APIResponse> deleteCategory( @PathVariable Integer id) {
	this.postService.delete(id);		
		return new ResponseEntity<APIResponse>(new APIResponse("Post Deleted Sucessfully", true), HttpStatus.OK);
	}
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<PostResponse> getpostSearch(@PathVariable String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "postId",required = false) String sortDirection) {
		return ResponseEntity.ok(this.postService.searchPost(keyword,pageNumber,pageSize,sortBy,sortDirection));

	}
	@GetMapping("/post/search1/{keyword}")
	public ResponseEntity<List<PostDTO>> getpostbyId1(@PathVariable String keyword){
		return ResponseEntity.ok(this.postService.searchTitle(keyword));

	}
}
