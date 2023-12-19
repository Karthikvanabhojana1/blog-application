package com.karthik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.blog.entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

}
