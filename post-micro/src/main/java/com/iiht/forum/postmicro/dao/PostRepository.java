package com.iiht.forum.postmicro.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forum.postmicro.document.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String>{

	public List<Post> findAllByUserId(String userId);
}
