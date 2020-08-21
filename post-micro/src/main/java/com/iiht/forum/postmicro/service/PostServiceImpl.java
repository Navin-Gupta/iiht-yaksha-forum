package com.iiht.forum.postmicro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.postmicro.dao.PostRepository;
import com.iiht.forum.postmicro.document.Post;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailListDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.feignproxy.UserProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PostServiceImpl implements PostService {

	
	
	// private static String USER_URL = "http://localhost:9091/api/user";
	// private RestTemplate restTemplate;
	private CommentService commentService;
	private PostRepository repository;
	private UserProxy proxy;
	public PostServiceImpl(PostRepository repository, 
						   // RestTemplate restTemplate, 
						   CommentService commentService,
						   UserProxy proxy) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		// this.restTemplate = restTemplate;
		this.commentService = commentService;
		this.proxy = proxy;
	}
	
	@Override
	
	public PostDetailDto addPost(PostDto post, String userId) {
		// TODO Auto-generated method stub
		Post postDb = new Post(null, 
							  post.getTitle(), 
							  post.getTags(), 
							  post.getPost(), 
							  userId, 
							  LocalDateTime.now(), 
							  0);
		postDb = this.repository.save(postDb);
		// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + userId, UserDetailDto.class);
		UserDetailDto userDetail = this.proxy.getUserDetails(userId).getBody();
		List<CommentDetailDto> comments = this.commentService.getComments(postDb.getId());
		
		//PostDetailDto postDetail = new PostDetailDto(postId, title, tags, post, postedByUser, postedOn, likes, comments);
		PostDetailDto postDetail = new PostDetailDto(postDb.getId(), 
													 postDb.getTitle(), 
													 postDb.getTags(), 
													 postDb.getPost(), 
													 userDetail, 
													 postDb.getPostedOn(), 
													 postDb.getLikes(), 
													 comments);
		
		
		return postDetail;
	}
	
	

	@Override
	public PostDetailDto getPost(String postId) {
		// TODO Auto-generated method stub
		Post postDb = this.repository.findById(postId).orElse(null);
		if(postDb != null) {
			// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + postDb.getUserId(), UserDetailDto.class);
			UserDetailDto userDetail = this.proxy.getUserDetails(postDb.getUserId()).getBody();
			List<CommentDetailDto> comments = this.commentService.getComments(postDb.getId());
			
			//PostDetailDto postDetail = new PostDetailDto(postId, title, tags, post, postedByUser, postedOn, likes, comments);
			PostDetailDto postDetail = new PostDetailDto(postDb.getId(), 
														 postDb.getTitle(), 
														 postDb.getTags(), 
														 postDb.getPost(), 
														 userDetail, 
														 postDb.getPostedOn(), 
														 postDb.getLikes(), 
														 comments);
			
			
			return postDetail;
		}
		return null;
	}

	@Override
	public Integer addLike(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDetailListDto getPosts(String userId) {
		// TODO Auto-generated method stub
		List<Post> posts = this.repository.findAllByUserId(userId);
		PostDetailListDto postList = new PostDetailListDto();
		postList.setUserId(userId);
		
		List<PostDetailDto> postDetails = new ArrayList<PostDetailDto>();
		for(Post postDb : posts) {
			// UserDetailDto userDetail = this.restTemplate.getForObject(USER_URL + "/get/" + userId, UserDetailDto.class);
			UserDetailDto userDetail = this.proxy.getUserDetails(postDb.getUserId()).getBody();
			List<CommentDetailDto> comments = this.commentService.getComments(postDb.getId());
			
			//PostDetailDto postDetail = new PostDetailDto(postId, title, tags, post, postedByUser, postedOn, likes, comments);
			PostDetailDto postDetail = new PostDetailDto(postDb.getId(), 
														 postDb.getTitle(), 
														 postDb.getTags(), 
														 postDb.getPost(), 
														 userDetail, 
														 postDb.getPostedOn(), 
														 postDb.getLikes(), 
														 comments);
			postDetails.add(postDetail);
		}
		postList.setPostDetailDtos(postDetails);
		return postList;
	}

}
