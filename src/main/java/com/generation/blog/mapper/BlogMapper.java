package com.generation.blog.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.blog.dto.BlogDTO;
import com.generation.blog.model.Blog;
import com.generation.blog.model.WebUser;

@Mapper(componentModel="spring")
public interface BlogMapper
{
	@Mapping(source = "owner.id", target = "ownerId")
	BlogDTO toDTO (Blog blog);
	List<BlogDTO> toDTOs (List<Blog> blogs);
	
	@Mapping(source = "ownerId", target = "owner.id")
	Blog toEntity (BlogDTO dto);
	List<Blog> toEntities (List<BlogDTO> dtos);

}
