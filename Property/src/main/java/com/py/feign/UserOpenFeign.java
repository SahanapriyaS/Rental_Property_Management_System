package com.py.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.py.dto.response.OwnerDTO;


@FeignClient(name = "UserRegistration", url = "http://localhost:8080")
public interface UserOpenFeign {

    @GetMapping("/users/{id}")
    OwnerDTO getUserById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
}



