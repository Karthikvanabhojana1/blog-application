package com.karthik.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@Column(length = 100, nullable = false)
	private String postTitle;
	private String imageName;
	@Column(length = 10000, nullable = false)
	private String content;
	private Date addedDate;
	
	@ManyToOne
	private Categories categories;
	@ManyToOne
	private User user;
}
