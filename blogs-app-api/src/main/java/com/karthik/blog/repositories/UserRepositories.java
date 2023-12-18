 package com.karthik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.blog.entities.User;

public interface UserRepositories extends JpaRepository<User, Integer>{

}
