package com.karthik.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user,Pageable p);
	Page<Post> findByCategories(Categories categories,Pageable p);
	@Query("SELECT p FROM Post p WHERE p.content LIKE :key")
    List<Post> searchByTitle(@Param("key") String title);
	@Query("SELECT p FROM Post p WHERE p.content LIKE :content")
	Page<Post> searchByContent(@Param("content") String content, Pageable pageable);

}
