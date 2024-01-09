package com.karthik.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.Comment;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.payloads.CommentDTO;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.repositories.CommentsRepository;
import com.karthik.blog.repositories.PostRepository;
import com.karthik.blog.repositories.UserRepositories;
import com.karthik.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
@Autowired
private CommentsRepository commentRepository;
@Autowired
private PostRepository postRepo;
@Autowired
private UserRepositories  userrepositories;
@Autowired
private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId,Integer userId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", " Post Id", postId));		
		User user = this.userrepositories.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		
		Comment comment=this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
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

	@Override
	public List<CommentDTO> getCommentAll() {
		// TODO Auto-generated method stub
		 List<Comment> listofcomment=this.commentRepository.findAll();
			List<CommentDTO> listUserDTO = listofcomment.stream().map(comment -> this.commenttodto(comment)).collect(Collectors.toList());
//			List<UserDTO> listUserDTO = listofuser.stream().map(user -> this.Usertodto(user)).collect(Collectors.toList());

		return listUserDTO;

	}
	private Comment dtotoComment(CommentDTO commentDTO) {
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		return comment;
	}

	private CommentDTO commenttodto(Comment comment) {
		CommentDTO commentDTO = this.modelMapper.map(comment, CommentDTO.class);
		return commentDTO;
	}

}
