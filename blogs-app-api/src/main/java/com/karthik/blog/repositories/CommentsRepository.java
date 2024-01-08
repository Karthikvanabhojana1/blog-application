package com.karthik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.blog.entities.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Integer>{

}
