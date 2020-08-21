package com.iiht.forum.postmicro.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iiht.forum.postmicro.dto.UserDetailDto;



@FeignClient(name = "api-gateway") // for passing all request through API Gateway
//configure the Ribbon to load balance
@RibbonClient(name = "movie-service") // will activate load balancing on movie-service
public interface UserProxy {
	@GetMapping("/user-micro/api/user/get/{id}")
	public ResponseEntity<UserDetailDto> getUserDetails(@PathVariable("id") String id);
}
