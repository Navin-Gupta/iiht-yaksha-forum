package com.iiht.forum.usermicro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iiht.forum.usermicro.dao.UserDetailsRepository;
import com.iiht.forum.usermicro.document.UserDetail;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;
import com.iiht.forum.usermicro.util.StorageUtil;

@Service
public class UserServiceImpl implements UserService {

	private UserDetailsRepository repository;
	
	private StorageUtil storageUtil;
	
	
	public UserServiceImpl(UserDetailsRepository repository, StorageUtil storageUtil) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.storageUtil = storageUtil;
	}
	
	@Override
	public UserDetailDto login(String emailId, String password) {
		// TODO Auto-generated method stub
		List<UserDetail> userDetails = this.repository.findByEmailIdAndPassword(emailId, password);
		if(userDetails.size() > 0) {
			UserDetail userDetail = userDetails.get(0);
			// new UserDetailDto(id, emailId, firstName, lastName, mobileNumber, profilePic);
			UserDetailDto userDetailDto = new UserDetailDto(userDetail.getId(),
															 userDetail.getEmailId(),
															 userDetail.getFirstName(),
															 userDetail.getLastName(),
															 userDetail.getMobileNumber(),
															 userDetail.getProfilePic());
			return userDetailDto;
		}
		
		return null;
	}

	@Override
	public UserDetailDto register(MultipartFile multipartFile, RegisterDto registerDto) {
		// TODO Auto-generated method stub
		String path = this.storageUtil.store(multipartFile);
		// UserDetail userDetail = new UserDetail(id, emailId, password, firstName, lastName, mobileNumber, profilePic);
		UserDetail userDetail = new UserDetail(null,
												registerDto.getEmailId(),
												registerDto.getPassword(),
												registerDto.getFirstName(),
												registerDto.getLastName(),
												registerDto.getMobileNumber(),
												path);
		userDetail = this.repository.save(userDetail);
		UserDetailDto userDetailDto = new UserDetailDto(userDetail.getId(),
				 userDetail.getEmailId(),
				 userDetail.getFirstName(),
				 userDetail.getLastName(),
				 userDetail.getMobileNumber(),
				 userDetail.getProfilePic());
		return userDetailDto;
	}

	@Override
	public boolean checkAlreadyInUse(String emailId) {
		// TODO Auto-generated method stub
		return this.repository.findByEmailId(emailId).size() > 0;
		
	}

	@Override
	public UserDetailDto getUserDetails(String id) {
		// TODO Auto-generated method stub
		UserDetail userDetail = this.repository.findById(id).orElse(null);
		if(userDetail != null) {
			UserDetailDto userDetailDto = new UserDetailDto(userDetail.getId(),
															 userDetail.getEmailId(),
															 userDetail.getFirstName(),
															 userDetail.getLastName(),
															 userDetail.getMobileNumber(),
															 userDetail.getProfilePic());
			return userDetailDto;
		}
		
		return null;
	}

	@Override
	public List<UserDetailDto> getRegisteredUserList() {
		// TODO Auto-generated method stub
		Iterable<UserDetail> userDetails = this.repository.findAll();
		List<UserDetailDto> userDetailDtos = new ArrayList<UserDetailDto>();
		for(UserDetail userDetail : userDetails) {
			UserDetailDto userDetailDto = new UserDetailDto(userDetail.getId(),
															 userDetail.getEmailId(),
															 userDetail.getFirstName(),
															 userDetail.getLastName(),
															 userDetail.getMobileNumber(),
															 userDetail.getProfilePic());
			userDetailDtos.add(userDetailDto);
		}
		return userDetailDtos;
	}

}
