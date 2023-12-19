 package com.karthik.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.blog.entities.User;

public interface UserRepositories extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

}
