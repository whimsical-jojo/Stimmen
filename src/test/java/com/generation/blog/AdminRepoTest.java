package com.generation.blog;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.repository.UserRepository;
import com.generation.blog.service.UserService;

//Works and adds a Wajojo evil serious and not at all whimsical admin
@SpringBootTest
class AdminRepoTest
{

	@Autowired
	UserService service;
	
	@Test
	void test()
	{
		AdminDTO admin = new AdminDTO();
		admin.setUsername("SeriousWajojo");
		admin.setEmail("wajojo@evil.com");
		admin.setPassword("ayyylmao");
		admin.setLastName("Waandrade");
		admin.setFirstName("Wajojo");
		admin.setRole("Admin");
		admin.setLastPasswordChange(LocalDate.now());
		
		admin = (AdminDTO) service.save(admin);
		System.out.println(admin.getPassword());
		assert(!admin.getPassword().equals("ayyylmao")); //should return hashed
		
		//service.deleteById(2);
	}

}
