package com.iiht.forum.postmicro.service;



import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailListDto;
import com.iiht.forum.postmicro.dto.PostDto;

public interface PostService {

	public PostDetailDto addPost(PostDto post, String userId);
	public PostDetailDto getPost(String postId);
	public Integer addLike(String postId);
	public PostDetailListDto getPosts(String userId);
}
