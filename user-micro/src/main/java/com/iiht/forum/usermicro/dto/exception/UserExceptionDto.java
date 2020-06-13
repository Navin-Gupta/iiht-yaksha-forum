package com.iiht.forum.usermicro.dto.exception;

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
public class UserExceptionDto {
	private String message;
	private Integer errorCode;
	private Long timeStamp;
	

}
