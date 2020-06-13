package com.iiht.forum.usermicro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iiht.forum.usermicro.document.UserDetail;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;

public interface UserService {

	UserDetailDto login(String emailId, String password);
	UserDetailDto register(MultipartFile multipartFile, RegisterDto registerDto);
	boolean checkAlreadyInUse(String emailId);
	UserDetailDto getUserDetails(String id);
	List<UserDetailDto> getRegisteredUserList();
}
