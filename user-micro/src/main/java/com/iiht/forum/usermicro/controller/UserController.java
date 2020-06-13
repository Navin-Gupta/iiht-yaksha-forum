package com.iiht.forum.usermicro.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iiht.forum.usermicro.dto.LoginDto;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;
import com.iiht.forum.usermicro.dto.exception.UserExceptionDto;
import com.iiht.forum.usermicro.exception.BoundaryException;
import com.iiht.forum.usermicro.exception.UserNotFoundException;
import com.iiht.forum.usermicro.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

	private UserService service;
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDetailDto> register(@RequestParam("file")MultipartFile multiPartFile, 
												  @RequestParam("emailId") String emailId,
												  @RequestParam("firstName") String firstName,
												  @RequestParam("lastName") String lastName,
												  @RequestParam("password") String password,
												  @RequestParam("confirmPassword") String confirmPassword,
												  @RequestParam("mobileNumber") String mobileNumber){
		
		RegisterDto registerDto = new RegisterDto(emailId, password, confirmPassword, firstName, lastName, mobileNumber);
		UserDetailDto userDetailDto = this.service.register(multiPartFile, registerDto);
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDetailDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult result){
		
		if(result.hasErrors()) {
			throw new BoundaryException("Password Invalid!!!");
		}
		UserDetailDto userDetailDto = this.service.login(loginDto.getEmailId(), loginDto.getPassword());
		if(userDetailDto != null) {
			System.out.println("\n\nNOT NULL\n\n");
			ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.OK);
			return response;
		}
		throw new UserNotFoundException("Invalid Credentials!!!");
	}
	
	@GetMapping("/check-in-use/{emailId}")
	public ResponseEntity<Boolean> checkAlreadyInUse(@PathVariable("emailId") String emailId){
		Boolean status = this.service.checkAlreadyInUse(emailId);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(status, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDetailDto> getUserDetails(@PathVariable("id") String id){
		UserDetailDto userDetailDto = this.service.getUserDetails(id);
		if(userDetailDto !=null) {
			
			ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.OK);
			return response;
		}
		System.out.println("getting to exception");
		throw new UserNotFoundException("User with Id - " + id + " not found!!!");
	}
	
	@GetMapping("/all-users")
	public ResponseEntity<List<UserDetailDto>> getRegisteredUserList(){
		List<UserDetailDto> userDetailDtos =  this.service.getRegisteredUserList();
		ResponseEntity<List<UserDetailDto>> response = new ResponseEntity<List<UserDetailDto>>(userDetailDtos, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserExceptionDto> userExceptionHandler(UserNotFoundException ex){
		System.out.println("Inside handler");
		UserExceptionDto userExceptionDto = new UserExceptionDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.NOT_FOUND);
		return response;
	}
	@ExceptionHandler(BoundaryException.class)
	public ResponseEntity<UserExceptionDto> boundaryExceptionHandler(BoundaryException ex){
		System.out.println("Inside handler");
		UserExceptionDto userExceptionDto = new UserExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = new ResponseEntity<UserExceptionDto>(userExceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
