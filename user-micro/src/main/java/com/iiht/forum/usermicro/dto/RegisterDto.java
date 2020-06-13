package com.iiht.forum.usermicro.dto;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString	
public class RegisterDto {
	private String emailId;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	
	private String mobileNumber;
	
}
