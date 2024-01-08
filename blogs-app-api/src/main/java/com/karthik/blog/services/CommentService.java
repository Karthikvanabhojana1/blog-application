package com.karthik.blog.services;

import com.karthik.blog.payloads.CommentDTO;

public interface CommentService {
 CommentDTO createComment(CommentDTO commentDTO, Integer postId) ;
void deletePost(Integer commentId);
}
