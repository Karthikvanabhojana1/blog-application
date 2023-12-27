package com.karthik.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	@Query
	List<Post> findByUser(User user);
	List<Post> findByCategories(Categories categories);
}
