package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.dto.BloggerDTO;
import com.generation.blog.dto.UserDTO;
import com.generation.blog.entities.Admin;
import com.generation.blog.entities.Blogger;
import com.generation.blog.entities.User;

@Mapper(componentModel="spring")
public interface UserMapper
{
	@SubclassMapping(source = Admin.class, target = AdminDTO.class)
	@SubclassMapping(source = Blogger.class, target = BloggerDTO.class)
	UserDTO toDTO (User user);
	List<UserDTO> toDTOs (List<User> users);
	
	@SubclassMapping(source = BloggerDTO.class, target = Blogger.class)
	@SubclassMapping(source = AdminDTO.class, target = Admin.class)
	User toEntity (UserDTO dto);
	List<User> toEntities (List<UserDTO> dtos);
}
