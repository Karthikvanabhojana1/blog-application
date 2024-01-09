package com.karthik.blog.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.payloads.APIResponse;
import com.karthik.blog.payloads.CommentDTO;
import com.karthik.blog.services.CommentService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@PostMapping("/user/{userId}/post/{postId}/addcomment")
	public ResponseEntity<CommentDTO> createComments(@RequestParam("commentContent") @NotBlank String commentContent,@PathVariable Integer postId,@PathVariable Integer userId){
		CommentDTO commentDTO=new CommentDTO();
		commentDTO.setContent(commentContent);
CommentDTO commentNew=this.commentService.createComment(commentDTO, postId,userId);
		return new ResponseEntity<>(commentNew, HttpStatus.CREATED);

	}
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<APIResponse> deleteComment( @PathVariable Integer commentId) {
	this.commentService.deletePost(commentId);	
		return new ResponseEntity<APIResponse>(new APIResponse("Comment Deleted Sucessfully", true), HttpStatus.OK);
	}
}
