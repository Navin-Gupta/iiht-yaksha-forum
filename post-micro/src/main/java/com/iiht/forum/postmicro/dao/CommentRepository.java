package com.iiht.forum.postmicro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forum.postmicro.document.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String>{
	List<Comment> findByPostId(String postId);
}
