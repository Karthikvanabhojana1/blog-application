package com.karthik.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.Comment;
import com.karthik.blog.entities.Post;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.payloads.CommentDTO;
import com.karthik.blog.repositories.CommentsRepository;
import com.karthik.blog.repositories.PostRepository;
import com.karthik.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
@Autowired
private CommentsRepository commentRepository;
@Autowired
private PostRepository postRepo;
@Autowired
private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", " Post Id", postId));		
		
		Comment comment=this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment  savedComment=this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public  void deletePost(Integer commentId) {
		// TODO Auto-generated method stub
		Comment deleted = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", " CommentId Id", commentId));		
		this.commentRepository.delete(deleted);
	}

}
