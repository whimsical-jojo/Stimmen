package com.generation.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO
{
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String role;

	List<BlogDTO> blogs;
}
