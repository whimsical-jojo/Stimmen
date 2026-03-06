package com.generation.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.dto.LoginDTO;
import com.generation.blog.dto.TokenDTO;
import com.generation.blog.service.UserService;

//I dont think this is the ideal solution, but I just want it to work
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginAPI
{
	@Autowired
	UserService service;
	
	@PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {
        return service.login(loginDTO);
    }
}
