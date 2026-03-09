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
import com.generation.blog.model.Admin;
import com.generation.blog.model.WebUser;

//Works and adds a Wajojo evil serious and not at all whimsical adminDTO
@SpringBootTest
class AccountCreationTest
{

	@Autowired
	AccountManagementService service;

	@Autowired
	WebUserRepository userRepo;
	

	@Test
	void test()
	{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setUsername("SeriousWajojo");
		adminDTO.setEmail("wajojo@evil.com");
		adminDTO.setPassword("ayyylmao");
		adminDTO.setLastName("Waandrade");
		adminDTO.setFirstName("Wajojo");
		adminDTO.setRole("adminDTO");
		
		adminDTO = (AdminDTO) service.create(adminDTO);
		System.out.println(adminDTO.getPassword());
		assert(adminDTO.getPassword()==null); //should not be visible

		WebUser admin = userRepo.findById(adminDTO.getId()).get();
		assert(admin instanceof Admin);
		assert((Admin)admin).getLastPasswordChange().equals(LocalDate.now());
		//Would have to test that it properly saves the date of password changed and actually gets an adminDTO instead
		//of a regular user.
		//service.deleteById(2);
	}

}
