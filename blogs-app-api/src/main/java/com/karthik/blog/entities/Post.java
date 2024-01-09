package com.karthik.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.karthik.blog.payloads.CommentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JsonBackReference
	private Categories categories;
	@ManyToOne
    @JsonBackReference
	private User user;
	@JsonManagedReference
	@OneToMany(mappedBy = "post", cascade=CascadeType.ALL)
	private List<Comment> comments=new ArrayList<>();
}
