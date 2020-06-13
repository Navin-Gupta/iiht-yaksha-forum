package com.iiht.forum.postmicro.service;

import java.util.List;

import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;

public interface CommentService {
	public CommentDetailDto addComment(CommentDto comment, String postId, String userId);
	public CommentDetailDto getComment(String commentId);
	public Integer addLike(String commentId);
	public List<CommentDetailDto> getComments(String postId);
}
