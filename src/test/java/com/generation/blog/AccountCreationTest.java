package com.generation.blog;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.repository.WebUserRepository;
import com.generation.blog.service.AccountManagementService;
import com.generation.blog.service.WebUserService;

//Works and adds a Wajojo evil serious and not at all whimsical admin
@SpringBootTest
class AccountCreationTest
{

	@Autowired
	AccountManagementService service;
	
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
		
		admin = (AdminDTO) service.save(admin);
		System.out.println(admin.getPassword());
		assert(!admin.getPassword().equals("ayyylmao")); //should return hashed
		
		//service.deleteById(2);
	}

}
