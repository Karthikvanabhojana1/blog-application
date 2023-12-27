package com.karthik.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.entities.Post;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;
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
	public ResponseEntity<List<PostDTO>> getAllUsers() {
		return ResponseEntity.ok(this.postService.getAllPosts());
	}

	@GetMapping("/post/category/{categoryId}")
	public ResponseEntity<List<PostDTO>> getpostbycategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(this.postService.getpostbyCategory(categoryId));
	}

	@GetMapping("/post/user/{userId}")
	public ResponseEntity<List<PostDTO>> getpostbyuser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.postService.getpostbyUser(userId));

	}
}
