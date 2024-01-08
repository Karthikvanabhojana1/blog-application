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
import org.springframework.web.bind.annotation.RestController;

import com.karthik.blog.payloads.APIResponse;
import com.karthik.blog.payloads.CommentDTO;
import com.karthik.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/addcomment")
	public ResponseEntity<CommentDTO> createComments(@RequestBody CommentDTO commentDTO,@PathVariable Integer postId){
           
CommentDTO commentNew=this.commentService.createComment(commentDTO, postId);
		return new ResponseEntity<>(commentNew, HttpStatus.CREATED);

	}
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<APIResponse> deleteComment( @PathVariable Integer commentId) {
	this.commentService.deletePost(commentId);	
		return new ResponseEntity<APIResponse>(new APIResponse("Comment Deleted Sucessfully", true), HttpStatus.OK);
	}
}
