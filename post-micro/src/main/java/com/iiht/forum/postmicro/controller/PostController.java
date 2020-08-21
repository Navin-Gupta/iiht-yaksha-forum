package com.iiht.forum.postmicro.controller;



import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailListDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.service.PostService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post")
public class PostController {

	private PostService postService;
	public PostController(PostService postService) {
		// TODO Auto-generated constructor stub
		this.postService = postService;
	}
	
	@PostMapping("/add/{userId}")
	@HystrixCommand(fallbackMethod = "defaultAdd")
	public ResponseEntity<PostDetailDto> addPost(@PathVariable("userId") String userId, @RequestBody PostDto post){
		PostDetailDto postDetail = this.postService.addPost(post, userId);
		ResponseEntity<PostDetailDto> response =
				new ResponseEntity<PostDetailDto>(postDetail, HttpStatus.OK);
		
		return response;
	}
	public ResponseEntity<PostDetailDto> defaultAdd(String userId,  PostDto post) {
		PostDetailDto postDetail = new PostDetailDto("", 
													 "", 
													 "", 
													 "", 
													 new UserDetailDto("", "", "", "", "", ""), 
													 LocalDateTime.now(), 
													 0, 
													 new ArrayList<CommentDetailDto>());
		ResponseEntity<PostDetailDto> response =
				new ResponseEntity<PostDetailDto>(postDetail, HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/get/{postId}")
	public ResponseEntity<PostDetailDto> getPost(@PathVariable("postId") String postId){
		PostDetailDto postDetail = this.postService.getPost(postId);
		ResponseEntity<PostDetailDto> response =
				new ResponseEntity<PostDetailDto>(postDetail, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/get-all/{userId}")
	public ResponseEntity<PostDetailListDto> getAllPostsForUser(@PathVariable("userId") String userId){
		PostDetailListDto postDetailList = this.postService.getPosts(userId);
		ResponseEntity<PostDetailListDto> response =
				new ResponseEntity<PostDetailListDto>(postDetailList, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/like/{postId}")
	public ResponseEntity<Integer> addLike(@PathVariable("postId") String postId){
		Integer likes = this.postService.addLike(postId);
		ResponseEntity<Integer> response = 
				new ResponseEntity<Integer>(likes, HttpStatus.OK);
		
		return response;
	}
}














