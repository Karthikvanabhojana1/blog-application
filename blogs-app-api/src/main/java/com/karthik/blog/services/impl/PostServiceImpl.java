package com.karthik.blog.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karthik.blog.entities.Categories;
import com.karthik.blog.entities.Comment;
import com.karthik.blog.entities.Post;
import com.karthik.blog.entities.User;
import com.karthik.blog.exception.ResourceNotFoundException;
import com.karthik.blog.payloads.CategoryDTO;
import com.karthik.blog.payloads.PostDTO;
import com.karthik.blog.payloads.PostResponse;
import com.karthik.blog.payloads.UserDTO;
import com.karthik.blog.repositories.CategoryRepository;
import com.karthik.blog.repositories.CommentsRepository;
import com.karthik.blog.repositories.PostRepository;
import com.karthik.blog.repositories.UserRepositories;
import com.karthik.blog.services.CommentService;
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
	@Autowired
	private CommentsRepository commentRepo;
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId,String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " User Id", userId));
		Categories category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryId));

		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setAddedDate(new Date());
		post.setCategories(category);
		post.setUser(user);
		Post newPost = this.postRepo.save(post);
		
		
		String name=file.getOriginalFilename();
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		//Full Path
		
		String filePath=path+File.separator+fileName1;
		//create Image folder if not created
		File f=new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
	
		
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		post.setImageName(filePath);

		return this.modelMapper.map(newPost, PostDTO.class);

	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", " Post Id", postId));

		post.setPostTitle(postDTO.getPostTitle());
		post.setContent(postDTO.getContent());
		Post updated = this.postRepo.save(post);

		return this.modelMapper.map(updated, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findAll(p);
		List<Post> listofPosts = pagepost.getContent();
		List<PostDTO> listofPostsDTO = listofPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postresponse = new PostResponse();
		postresponse.setContent(listofPostsDTO);
		postresponse.setPageNumber(pagepost.getNumber());
		postresponse.setPagesize(pagepost.getSize());
		postresponse.setTotalElements(pagepost.getTotalElements());
		postresponse.setTotalPages(pagepost.getTotalPages());
		postresponse.setLastpage(pagepost.isLast());
		return postresponse;

	}

	@Override
	public PostDTO getpostbyId(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", " Post Id", postId));

PostDTO postdto=this.modelMapper.map(post, PostDTO.class);

		return postdto;
	}

	@Override
	public PostResponse getpostbyCategory(Integer categoryId, int pageNumber, int pageSize, String sortBy,
			String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Categories categories = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", " Category Id", categoryId));
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findByCategories(categories, p);

		List<Post> listofPosts = pagepost.getContent();

		List<PostDTO> listofpostsbycategory = listofPosts.stream()
				.map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

//	
//	
//	Pageable p=PageRequest.of(pageNumber, pageSize);
//	
//	Page<Post> pagepost = this.postRepo.findAll(p);
//	List<Post> listofPosts = pagepost.getContent();
//	List<PostDTO> listofPostsDTO =listofPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
//	

		PostResponse postresponsebycategory = new PostResponse();
		postresponsebycategory.setContent(listofpostsbycategory);
		postresponsebycategory.setPageNumber(pagepost.getNumber());
		postresponsebycategory.setPagesize(pagepost.getSize());
		postresponsebycategory.setTotalElements(pagepost.getTotalElements());
		postresponsebycategory.setTotalPages(pagepost.getTotalPages());
		postresponsebycategory.setLastpage(pagepost.isLast());
		return postresponsebycategory;

	}

	@Override
	public PostResponse getpostbyUser(Integer userId, int pageNumber, int pageSize, String sortBy,
			String sortDirection) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findByUser(user, p);
		List<Post> listofPosts = pagepost.getContent();

		List<PostDTO> listofpostbyUser = listofPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postresponsebyuser = new PostResponse();
		postresponsebyuser.setContent(listofpostbyUser);
		postresponsebyuser.setPageNumber(pagepost.getNumber());
		postresponsebyuser.setPagesize(pagepost.getSize());
		postresponsebyuser.setTotalElements(pagepost.getTotalElements());
		postresponsebyuser.setTotalPages(pagepost.getTotalPages());
		postresponsebyuser.setLastpage(pagepost.isLast());
		return postresponsebyuser;

	}

	@Override
	public PostResponse searchPost(String keywords, int pageNumber, int pageSize, String sortBy, String sortDirection) {
		// TODO Auto-generated method stub\

		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.searchByContent("%"+keywords+"%", p);
		List<Post> listofPosts = pagepost.getContent();

		List<PostDTO> listofpostbyUser = listofPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postresponsebyuser = new PostResponse();
		postresponsebyuser.setContent(listofpostbyUser);
		postresponsebyuser.setPageNumber(pagepost.getNumber());
		postresponsebyuser.setPagesize(pagepost.getSize());
		postresponsebyuser.setTotalElements(pagepost.getTotalElements());
		postresponsebyuser.setTotalPages(pagepost.getTotalPages());
		postresponsebyuser.setLastpage(pagepost.isLast());
		return postresponsebyuser;
	}

	@Override
	public void delete(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", " Post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDTO> searchTitle(String keyword) {
		// TODO Auto-generated method stub
		List<Post> listpost=postRepo.searchByTitle(keyword);

		List<PostDTO> listofpostbyUser = listpost.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return listofpostbyUser;
	}
	
	
	

}
