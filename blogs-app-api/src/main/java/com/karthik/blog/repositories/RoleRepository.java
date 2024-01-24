package com.karthik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.blog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
