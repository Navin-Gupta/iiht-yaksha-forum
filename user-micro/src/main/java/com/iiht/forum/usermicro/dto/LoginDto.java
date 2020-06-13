package com.iiht.forum.usermicro.dto;

import org.hibernate.validator.constraints.Length;

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
public class LoginDto {
	private String emailId;
	@Length(min = 6)
	private String password;

}
