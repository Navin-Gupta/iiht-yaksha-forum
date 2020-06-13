package com.iiht.forum.usermicro.exception;

import static com.iiht.forum.usermicro.testutils.TestUtils.*;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.forum.usermicro.controller.UserController;
import com.iiht.forum.usermicro.service.UserServiceImpl;
import com.iiht.forum.usermicro.testutils.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class ExceptionTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
		
	
	@Test
	public void testGetUserDetailsFailed() throws Exception {
		when(userService.getUserDetails("2"))
		.thenReturn(null);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/2")
										.accept(MediaType.APPLICATION_JSON);
				
										
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
			
		yakshaAssert(currentTest(), 
					(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), 
					exceptionTestFile);
	}
}
