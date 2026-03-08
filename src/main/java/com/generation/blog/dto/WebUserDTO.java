package com.generation.blog.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebUserDTO
{
	//I see you looking at this and going: Oh well, shouldn't this DTO also have the blogs and posts from this user or something?
	//But what if we are loading comments or something and only need basic information like a username? 
	//It remains to be seen whether in some cases sending a huge DTO is better than slowly loading up what's necessary, but yeah,
	//I think in most cases, it's better to keep the DTOs the POJOest POJOs.
	//Or maybe I should just have different DTOs for different cases.
	private int id;
	//These are optional and only get sent if the user sends them, and sent back if they are visible
	private String firstName;
	private String lastName;
	private LocalDate dob;

	private String username;
	//This only ever gets sent in case of user creation or a password change. NEVER sent back from the DB for obvious reasons.
	private String password;
	private String email;
	private String role;
}
