package com.karthik.blog.services;

import java.util.List;

import com.karthik.blog.payloads.CommentDTO;

public interface CommentService {
 CommentDTO createComment(CommentDTO commentDTO, Integer postId,Integer userId) ;
void deletePost(Integer commentId);
List<CommentDTO> getCommentAll();

}
