package com.iiht.forum.postmicro.controller;


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
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.service.CommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {

	private CommentService commentService;
	public CommentController(CommentService commentService) {
		// TODO Auto-generated constructor stub
		this.commentService = commentService;
	}
	@PostMapping("/add/{postId}/{userId}")
	public ResponseEntity<CommentDetailDto> addComment(@PathVariable("postId") String postId, 
													   @PathVariable("userId") String userId,
													   @RequestBody CommentDto comment){
		CommentDetailDto commentDetail = this.commentService.addComment(comment, postId, userId);
		ResponseEntity<CommentDetailDto> response = 
				new ResponseEntity<CommentDetailDto>(commentDetail, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/like/{commentId}")
	public ResponseEntity<Integer> addLike(@PathVariable("commentId") String commentId){
		Integer likes = this.commentService.addLike(commentId);
		ResponseEntity<Integer> response = 
				new ResponseEntity<Integer>(likes, HttpStatus.OK);
		
		return response;
	}
	
	
}
