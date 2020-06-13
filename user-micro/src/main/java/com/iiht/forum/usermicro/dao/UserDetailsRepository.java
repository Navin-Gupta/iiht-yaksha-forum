package com.iiht.forum.usermicro.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forum.usermicro.document.UserDetail;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetail, String>{
	List<UserDetail> findByEmailIdAndPassword(String emailId, String password);
	List<UserDetail> findByEmailId(String emailId);
}
