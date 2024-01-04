package com.karthik.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.karthik.blog.payloads.APIResponse;
import com.karthik.blog.payloads.AppConstant;
import com.karthik.blog.payloads.PostDTO;
import com.karthik.blog.payloads.PostResponse;
import com.karthik.blog.services.PostService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController

@RequestMapping("/api")
public class PostController {
	@Autowired
	PostService postService;

	
	
	@Value("${project.image}")
	private String path;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost( @RequestParam("postTitle") @NotBlank String postTitle,
            @RequestParam("content") @NotBlank String content,
	        @PathVariable Integer userId, @PathVariable Integer categoryId,
	        @RequestParam("image") MultipartFile image) throws IOException {
		 PostDTO postDto = new PostDTO();
		    postDto.setPostTitle(postTitle);
		    postDto.setContent(content);
		PostDTO createPostDTO = this.postService.createPost(postDto, categoryId, userId,path,image);

		return new ResponseEntity<>(createPostDTO, HttpStatus.CREATED);

	}
	
	@GetMapping("post/getallposts")
	public ResponseEntity<PostResponse> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.sort_by,required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue =AppConstant.sort_dir,required = false) String sortDirection) {
		
		
		return ResponseEntity.ok(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection));
	}

	@GetMapping("/post/category/{categoryId}")
	public ResponseEntity<PostResponse> getpostbycategory(@PathVariable Integer categoryId,		@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.sort_by,required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue =AppConstant.sort_dir,required = false) String sortDirection) {
		return ResponseEntity.ok(this.postService.getpostbyCategory(categoryId,pageNumber,pageSize,sortBy,sortDirection));
	}

	@GetMapping("/post/user/{userId}")
	public ResponseEntity<PostResponse> getpostbyuser(@PathVariable Integer userId,	@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.sort_by,required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue =AppConstant.sort_dir,required = false) String sortDirection) {
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
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.sort_by,required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue =AppConstant.sort_dir,required = false) String sortDirection) {
		return ResponseEntity.ok(this.postService.searchPost(keyword,pageNumber,pageSize,sortBy,sortDirection));

	}
	
}
