package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

import com.generation.blog.dto.AdminDTO;
import com.generation.blog.dto.BloggerDTO;
import com.generation.blog.dto.WebUserDTO;
import com.generation.blog.model.Admin;
import com.generation.blog.model.Blogger;
import com.generation.blog.model.WebUser;

@Mapper(componentModel="spring")
public interface WebUserMapper
{
	@SubclassMapping(source = Admin.class, target = AdminDTO.class)
	@SubclassMapping(source = Blogger.class, target = BloggerDTO.class)
	@Mapping(target = "password", ignore = true)
	WebUserDTO toDTO (WebUser user);
	List<WebUserDTO> toDTOs (List<WebUser> users);
	
	@SubclassMapping(source = BloggerDTO.class, target = Blogger.class)
	@SubclassMapping(source = AdminDTO.class, target = Admin.class)
	WebUser toEntity (WebUserDTO dto);
	List<WebUser> toEntities (List<WebUserDTO> dtos);
}
