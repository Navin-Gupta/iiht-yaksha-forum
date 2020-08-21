package com.iiht.forum.postmicro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.postmicro.dao.CommentRepository;
import com.iiht.forum.postmicro.document.Comment;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.feignproxy.UserProxy;

@Service
public class CommentServiceImpl implements CommentService {

	private static String USER_URL = "http://localhost:9091/api/user";
	
	// private RestTemplate restTemplate;
	private CommentRepository repository;
	private UserProxy proxy;
	
	public CommentServiceImpl(CommentRepository repository, 
								// RestTemplate restTemplate,
								UserProxy proxy) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.proxy = proxy;
		// this.restTemplate = restTemplate;
	}
	
	@Override
	public CommentDetailDto addComment(CommentDto comment, String postId, String userId) {
		// TODO Auto-generated method stub
		Comment commentDb =  new Comment(null, 
									   comment.getTags(), 
									   comment.getComment(), 
									   postId, 
									   userId, 
									   LocalDateTime.now(), 
									   0);
		commentDb = this.repository.save(commentDb);
		// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + userId, UserDetailDto.class);
		UserDetailDto userDetail = this.proxy.getUserDetails(userId).getBody();
		CommentDetailDto commentDetail = new CommentDetailDto(commentDb.getId(), 
															  commentDb.getComment(), 
															  commentDb.getTags(), 
															  userDetail, 
															  commentDb.getCommentedOn(), 
															  commentDb.getLikes());
		return commentDetail;
	}

	@Override
	public CommentDetailDto getComment(String commentId) {
		// TODO Auto-generated method stub
		Comment commentDb = this.repository.findById(commentId).orElse(null);
		if(commentDb != null) {
			// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + commentDb.getUserId(), UserDetailDto.class);
			UserDetailDto userDetail = this.proxy.getUserDetails(commentDb.getUserId()).getBody();
			CommentDetailDto commentDetail = new CommentDetailDto(commentDb.getId(), 
																  commentDb.getComment(), 
																  commentDb.getTags(), 
																  userDetail, 
																  commentDb.getCommentedOn(), 
																  commentDb.getLikes());
			return commentDetail;
		}
		return null;
	}

	@Override
	public Integer addLike(String commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDetailDto> getComments(String postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = this.repository.findByPostId(postId);
		List<CommentDetailDto> commentDetails = new ArrayList<CommentDetailDto>();
		for(Comment commentDb : comments) {
			// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + commentDb.getUserId(), UserDetailDto.class);
			UserDetailDto userDetail = this.proxy.getUserDetails(commentDb.getUserId()).getBody();
			CommentDetailDto commentDetail = new CommentDetailDto(commentDb.getId(), 
																  commentDb.getComment(), 
																  commentDb.getTags(), 
																  userDetail, 
																  commentDb.getCommentedOn(), 
																  commentDb.getLikes());
			commentDetails.add(commentDetail);
		}
		return commentDetails;
	}

}
