package com.karthik.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.repositories.CategoryRepository;
import com.karthik.blog.repositories.PostRepository;
import com.karthik.blog.repositories.UserRepositories;
import com.karthik.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private UserRepositories userRepo;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " User Id", userId));
		Categories category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryId));

		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName("Default.jpeg");
		post.setAddedDate(new Date());
		post.setCategories(category);
		post.setUser(user);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
		

	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<PostDTO> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> listofPosts = this.postRepo.findAll();
		List<PostDTO> listofPostsDTO =listofPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		

		return listofPostsDTO;

	}

	@Override
	public PostDTO getpostbyId(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getpostbyCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Categories categories = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryId));

		List<Post> listofPosts=this.postRepo.findByCategories(categories);
	List<PostDTO> listofpostsbycategory =listofPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		

		return listofpostsbycategory;	}

	@Override
	public List<PostDTO> getpostbyUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id", userId));


		List<Post> listofPosts=this.postRepo.findByUser(user);
	List<PostDTO> listofpostbyUser =listofPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		

		return listofpostbyUser;	}

	
	

	@Override
	public List<PostDTO> serarchPost(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer postId) {
		// TODO Auto-generated method stub

	}

}
