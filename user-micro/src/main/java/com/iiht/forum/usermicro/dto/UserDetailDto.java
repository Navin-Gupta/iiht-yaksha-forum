package com.iiht.forum.usermicro.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString	
public class UserDetailDto {

	private String id;
	private String emailId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String profilePic; 
}
