package com.karthik.blog.payloads;


import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
	private int id;
	private String content;

	private UserDTO user;
	private PostDTO post;
}
